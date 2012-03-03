package org.androidtransfuse.analysis.astAnalyzer;

import org.androidtransfuse.analysis.AnalysisContext;
import org.androidtransfuse.analysis.adapter.ASTMethod;
import org.androidtransfuse.annotations.*;
import org.androidtransfuse.model.InjectionNode;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * @author John Ericksen
 */
public class MethodCallbackAnalysis extends ASTAnalysisAdaptor {

    private Map<Class<?>, String> methodAnnotations = new HashMap<Class<?>, String>();

    public MethodCallbackAnalysis() {
        methodAnnotations.put(OnCreate.class, "onCreate");
        methodAnnotations.put(OnTouch.class, "onTouch");
        methodAnnotations.put(OnDestroy.class, "onDestroy");
        methodAnnotations.put(OnPause.class, "onPause");
        methodAnnotations.put(OnRestart.class, "onRestart");
        methodAnnotations.put(OnResume.class, "onResume");
        methodAnnotations.put(OnStart.class, "onStart");
        methodAnnotations.put(OnStop.class, "onStop");
        methodAnnotations.put(OnLowMemory.class, "onLowMemory");
        //application
        methodAnnotations.put(OnTerminate.class, "onTerminate");
    }

    @Override
    public void analyzeMethod(InjectionNode injectionNode, ASTMethod astMethod, AnalysisContext context) {
        for (Class<?> annotation : methodAnnotations.keySet()) {
            if (astMethod.isAnnotated((Class<Annotation>) annotation)) {
                addMethod(injectionNode, annotation, astMethod, context);
            }
        }
    }

    private void addMethod(InjectionNode injectionNode, Class<?> annotation, ASTMethod astMethod, AnalysisContext context) {

        if (!injectionNode.containsAspect(MethodCallbackAspect.class)) {
            injectionNode.addAspect(new MethodCallbackAspect());
        }
        MethodCallbackAspect methodCallbackToken = injectionNode.getAspect(MethodCallbackAspect.class);

        methodCallbackToken.addMethodCallback(methodAnnotations.get(annotation), astMethod, context.getSuperClassLevel());

    }
}