package octo.annotations.processing;

import java.io.IOException;
import java.util.Set;
import arc.util.Strings;
import com.squareup.javapoet.*;
import octo.annotations.Mod;
import com.squareup.javapoet.TypeSpec.Builder;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes("octo.annotations.Mod")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ModProcess extends AbstractProcessor {
    public boolean done = false;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if(this.done) {
            return false;
        }

        Object[] elements = roundEnv.getRootElements().toArray();
        if(elements.length > 0) {
            Element element = (Element) elements[0];
            Mod mod = element.getAnnotation(Mod.class);

            String className = Strings.kebabToCamel(mod.name());
            className = className.substring(0, 1).toUpperCase() + className.substring(1);

            ClassName str = ClassName.get(String.class);
            Builder spec = TypeSpec.classBuilder(className + "ModData")
                    .addModifiers(Modifier.PUBLIC)
                    .addJavadoc(className + " basic data")
                    .addField(
                            FieldSpec.builder(str, "prefix", Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
                                    .initializer("$S", mod.prefix())
                                    .addJavadoc("mod prefix")
                                    .build()
                    )
                    .addField(
                            FieldSpec.builder(str, "name", Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
                                    .initializer("$S", className)
                                    .addJavadoc("mod name")
                                    .build()
                    )
                    .addField(
                            FieldSpec.builder(str, "kebabName", Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
                                    .initializer("$S", mod.name())
                                    .addJavadoc("mod kebab name")
                                    .build()
                    );

            try {
                JavaFile.builder("octo.gen", spec.build())
                        .indent("    ")
                        .skipJavaLangImports(true)
                        .build()
                        .writeTo(this.processingEnv.getFiler());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        this.done = true;
        return false;
    }
}