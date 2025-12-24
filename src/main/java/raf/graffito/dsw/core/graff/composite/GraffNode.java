package raf.graffito.dsw.core.graff.composite;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.graffito.dsw.core.graff.model.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Project.class, name = "project"),
        @JsonSubTypes.Type(value = Presentation.class, name = "presentation"),
        @JsonSubTypes.Type(value = Slide.class, name = "slide"),
        @JsonSubTypes.Type(value = TextElement.class, name = "TextElement"),
        @JsonSubTypes.Type(value = LogoElement.class, name = "LogoElement"),
        @JsonSubTypes.Type(value = ImageElement.class, name = "ImageElement")
})
public abstract class GraffNode {
    @JsonIgnore
    private GraffNode parent;
    private String name;

    public GraffNode findByName(String name){
        if(this.name.equals(name)) return this;
        return null;
    }


}
