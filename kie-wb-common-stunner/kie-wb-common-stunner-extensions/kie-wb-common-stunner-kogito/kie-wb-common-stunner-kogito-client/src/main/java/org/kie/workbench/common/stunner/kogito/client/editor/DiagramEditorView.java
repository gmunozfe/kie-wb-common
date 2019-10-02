/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.workbench.common.stunner.kogito.client.editor;

import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.client.workbench.widgets.listbar.ResizeFlowPanel;
import org.uberfire.ext.editor.commons.client.BaseEditorViewImpl;

@Dependent
@Templated
public class DiagramEditorView
        extends BaseEditorViewImpl
        implements AbstractDiagramEditor.View {

    @DataField
    private ResizeFlowPanel editorPanel;

    protected DiagramEditorView() {
        //CDI proxy
    }

    @Inject
    public DiagramEditorView(final ResizeFlowPanel editorPanel) {
        this.editorPanel = editorPanel;
    }

    @Override
    public void onResize() {
        final Widget parent = getParent();
        if (parent != null) {
            final double w = parent.getOffsetWidth();
            final double h = parent.getOffsetHeight();
            setPixelSize((int) w, (int) h);
        }
        editorPanel.onResize();
    }

    @Override
    public void setWidget(final IsWidget widget) {
        editorPanel.clear();
        editorPanel.add(widget);
    }

    @PreDestroy
    public void destroy() {
        editorPanel.clear();
        editorPanel.removeFromParent();
    }
}