package octo.annotations.processing;

import octo.annotations.MainAnnotation;
import octo.core.graphics.ModIcons;
import com.squareup.javapoet.*;
import mindustry.gen.Icon;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Set;

@SupportedAnnotationTypes(MainAnnotation.path)
@SupportedSourceVersion(SourceVersion.RELEASE_16)
public class IconProcessor extends AbstractProcessor {
    public boolean done = false;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
    }

    @Override public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if(this.done) {
            return false;
        }

        this.processIcons();
        this.done = true;
        return false;
    }

    public void processIcons() {
        TypeSpec.Builder iconSpec = TypeSpec.classBuilder("IconManager").addModifiers(Modifier.PUBLIC)
                .addJavadoc("Used to load names for icons");

        MethodSpec.Builder load = MethodSpec.methodBuilder("load")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(TypeName.VOID);

        for(Field field : Icon.class.getFields()) {
            if(field.getGenericType()
                    .getTypeName().equals("arc.scene.style.TextureRegionDrawable"))
            {
                String name = field.getName();

                load.addStatement(
                        "$T." + name + ".setName($S)",
                        ClassName.get(Icon.class),
                        name
                );
            }
        }

        load.addStatement("$T.load()", ClassName.get(ModIcons.class));

        try {
            JavaFile.builder("octo.gen", iconSpec.addMethod(load.build()).build())
                    .indent("    ").skipJavaLangImports(true).build().writeTo(filer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}