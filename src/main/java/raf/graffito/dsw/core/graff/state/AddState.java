package raf.graffito.dsw.core.graff.state;

import com.sun.tools.javac.Main;
import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.graff.factory.SlideElementFactory;
import raf.graffito.dsw.core.graff.model.Slide;
import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.views.SlideView;
import raf.graffito.dsw.tree.model.GraffTree;
import raf.graffito.dsw.tree.model.GraffTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseEvent;
@Getter
@Setter
public class AddState implements State {
    @Setter private SlideElementFactory factory;

    public AddState() {}

    @Override
    public void misKliknut(double x, double y, SlideView slideView) {
        if(slideView == null) return;

        SlideElement element = factory.createSlideElement(slideView.getSlide());
        if(element == null) return;

        if(x == -1 && y == -1) {
            int canvasWidth = 800;
            int canvasHeight = 600;

            element.setX(((canvasWidth - element.getWidth()) / 2));
            element.setY(((canvasHeight - element.getHeight()) / 2));
        }else{
            element.setX((int) x);
            element.setY((int) y);
        }
        slideView.getSlide().addChild(element);

        GraffTreeItem noviCvor = new GraffTreeItem(element);
        DefaultMutableTreeNode selected = MainFrame.getInstance().getGraffTree().getSelectedNode();
        if (selected instanceof GraffTreeItem parentItem && parentItem.getGraffNode() == slideView.getSlide()) {
            MainFrame.getInstance().getGraffTree().addChild(parentItem, element);
        }
//        MainFrame.getInstance().getGraffTree().addChild((GraffTreeItem) selected, noviCvor.getGraffNode());
        slideView.getSlide().addChild(element);
    }
}
