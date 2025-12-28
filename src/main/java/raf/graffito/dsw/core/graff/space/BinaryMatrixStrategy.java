package raf.graffito.dsw.core.graff.space;

import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.gui.swing.views.SlideView;

public class BinaryMatrixStrategy implements SpaceCheckStrategy {

    @Override
    public boolean hasEnoughSpace(Slide slide, int newX, int newY, int newWidth, int newHeight) {
        int logicWidth = SlideView.LOGICAL_WIDTH;
        int logicHeight = SlideView.LOGICAL_HEIGHT;


        boolean[][] matrix = new boolean[logicWidth][logicHeight];

        for (GraffNode node : slide.getChildren()) {
            if (node instanceof SlideElement) {
                SlideElement el = (SlideElement) node;
                fillMatrix(matrix, el.getX(), el.getY(), el.getWidth(), el.getHeight(), logicWidth, logicHeight);
            }
        }

        fillMatrix(matrix, newX, newY, newWidth, newHeight, logicWidth, logicHeight);

        long occupiedPixels = 0;
        for (int x = 0; x < logicWidth; x++) {
            for (int y = 0; y < logicHeight; y++) {
                if (matrix[x][y]) {
                    occupiedPixels++;
                }
            }
        }

        double totalPixels = logicWidth * logicHeight;
        double usageRatio = occupiedPixels / totalPixels;

        System.out.println("[Matrix Strategy] Zauzeto: " + String.format("%.2f", usageRatio * 100) + "%");

        return usageRatio <= 0.80;
    }

    private void fillMatrix(boolean[][] matrix, int x, int y, int width, int height, int maxW, int maxH) {
        int startX = Math.max(0, x);
        int startY = Math.max(0, y);
        int endX = Math.min(maxW, x + width);
        int endY = Math.min(maxH, y + height);

        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                matrix[i][j] = true;
            }
        }
    }
}
