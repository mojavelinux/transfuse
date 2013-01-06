/**
 * Copyright 2013 John Ericksen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.androidtransfuse.analysis.astAnalyzer;

import com.google.common.collect.ImmutableSet;
import org.androidtransfuse.adapter.ASTMethod;
import org.androidtransfuse.adapter.ASTType;
import org.androidtransfuse.analysis.AnalysisContext;
import org.androidtransfuse.annotations.*;
import org.androidtransfuse.model.InjectionNode;

import java.lang.annotation.Annotation;

/**
 * Analyzes the given class for listener annotations.  Adds these annotated methods to a ListenerAspect for
 * code generation during the generation phase.
 *
 * @author John Ericksen
 */
public class ListenerAnalysis extends ASTAnalysisAdaptor {

    private final ImmutableSet<Class<? extends Annotation>> methodAnnotations;

    public ListenerAnalysis() {
        ImmutableSet.Builder<Class<? extends Annotation>> eventListenerAnnotations = ImmutableSet.builder();
        eventListenerAnnotations.add(OnCreate.class);
        eventListenerAnnotations.add(OnDestroy.class);
        eventListenerAnnotations.add(OnPause.class);
        eventListenerAnnotations.add(OnRestart.class);
        eventListenerAnnotations.add(OnResume.class);
        eventListenerAnnotations.add(OnStart.class);
        eventListenerAnnotations.add(OnStop.class);
        eventListenerAnnotations.add(OnLowMemory.class);
        eventListenerAnnotations.add(OnSaveInstanceState.class);
        eventListenerAnnotations.add(OnRestoreInstanceState.class);
        eventListenerAnnotations.add(OnBackPressed.class);
        eventListenerAnnotations.add(OnTerminate.class);
        eventListenerAnnotations.add(OnConfigurationChanged.class);
        eventListenerAnnotations.add(OnListItemClick.class);
        eventListenerAnnotations.add(OnReceive.class);
        eventListenerAnnotations.add(OnRebind.class);
        eventListenerAnnotations.add(OnActivityCreated.class);
        eventListenerAnnotations.add(OnDestroyView.class);
        eventListenerAnnotations.add(OnDetach.class);

        this.methodAnnotations = eventListenerAnnotations.build();
    }

    @Override
    public void analyzeMethod(InjectionNode injectionNode, ASTType concreteType, ASTMethod astMethod, AnalysisContext context) {
        for (Class<? extends Annotation> annotation : methodAnnotations) {
            if (astMethod.isAnnotated(annotation)) {
                addMethod(injectionNode, annotation, astMethod);
            }
        }
    }

    private void addMethod(InjectionNode injectionNode, Class<? extends Annotation> annotation, ASTMethod astMethod) {

        if (!injectionNode.containsAspect(ListenerAspect.class)) {
            injectionNode.addAspect(new ListenerAspect());
        }
        ListenerAspect methodCallbackToken = injectionNode.getAspect(ListenerAspect.class);

        methodCallbackToken.addMethodCallback(annotation, astMethod);

        if (!annotation.equals(OnCreate.class)) {
            ASTInjectionAspect injectionAspect = injectionNode.getAspect(ASTInjectionAspect.class);

            if (injectionAspect == null) {
                injectionAspect = new ASTInjectionAspect();
                injectionNode.addAspect(ASTInjectionAspect.class, injectionAspect);
            }

            //injection node is now required outside of the local scope
            injectionAspect.setAssignmentType(ASTInjectionAspect.InjectionAssignmentType.FIELD);
        }

    }
}
