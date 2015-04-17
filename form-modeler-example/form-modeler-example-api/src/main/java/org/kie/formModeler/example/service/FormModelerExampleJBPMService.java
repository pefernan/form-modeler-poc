/*
 * Copyright 2012 JBoss by Red Hat.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.formModeler.example.service;

import org.jboss.errai.bus.server.annotations.Remote;
import org.kie.formModeler.model.FormMeta;

@Remote
public interface FormModelerExampleJBPMService {

    Long startProcessFromRenderContext( String ctxUID, String domainId, String processId, String correlationKey, FormMeta object );
    Long saveTaskStateFromRenderContext( String ctxUID, Long taskId, FormMeta object );
    void completeTaskFromContext( String ctxUID, Long taskId, String identityName, FormMeta object );
    void clearContext( String ctxUID );
}
