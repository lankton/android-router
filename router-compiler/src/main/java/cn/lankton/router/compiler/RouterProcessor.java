package cn.lankton.router.compiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import cn.lankton.router.annotation.Router;
import cn.lankton.router.annotation.RouterParam;

@AutoService(Processor.class)
public class RouterProcessor extends AbstractProcessor {
    private Filer mFiler; //文件相关的辅助类
    private Elements mElementUtils; //元素相关的辅助类
    private Messager mMessager; //日志相关的辅助类

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, "process process process");
        Map<String, String> map = new HashMap<>();
        Map<String, String[]> paramMap = new HashMap<>();
        for (Element element : roundEnv.getElementsAnnotatedWith(Router.class)) {
            TypeElement typeElement = (TypeElement) element;
            Router r = element.getAnnotation(Router.class);
            map.put(r.value(),typeElement.getQualifiedName().toString());
            RouterParam rp = element.getAnnotation(RouterParam.class);
            if (null != rp) {
                paramMap.put(r.value(), rp.value());
            }
        }
        try {
            generate(map, paramMap).writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(Router.class.getCanonicalName());
        mMessager.printMessage(Diagnostic.Kind.NOTE, "getSupportedAnnotationTypes " + Router.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        mMessager.printMessage(Diagnostic.Kind.NOTE, "getSupportedSourceVersion " + SourceVersion.latestSupported().toString());
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        mMessager.printMessage(Diagnostic.Kind.NOTE, "init init init");
    }

    private JavaFile generate(Map<String, String> map, Map<String, String[]> paramMap) {
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        for (String key : map.keySet()) {
            String[] paramStrs = paramMap.get(key);
            StringBuilder sb = new StringBuilder();
            if (null != paramStrs) {
                for (String str : paramStrs) {
                    sb.append(str).append("&");
                }
            }
            codeBlockBuilder.addStatement("RouterCenter.add(\"" + key + "\",\"" + map.get(key) + "\",\"" + sb.toString() + "\");");
        }
        // generate whole class
        TypeSpec finderClass = TypeSpec.classBuilder("RouterStaticInit")
                .addModifiers(Modifier.PUBLIC)
                .addStaticBlock(codeBlockBuilder.build())
                .build();

        // generate file
        return JavaFile.builder("io.github.lankton.router.library", finderClass).build();
    }
}
