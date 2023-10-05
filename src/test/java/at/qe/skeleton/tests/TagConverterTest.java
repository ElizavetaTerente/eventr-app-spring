package at.qe.skeleton.tests;

import at.qe.skeleton.model.Tag;
import at.qe.skeleton.services.TagService;
import at.qe.skeleton.ui.beans.TagConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseStream;
import javax.faces.context.ResponseWriter;
import javax.faces.el.ValueBinding;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;
import javax.faces.render.RenderKit;
import javax.faces.render.Renderer;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Transactional
@SpringBootTest
@WebAppConfiguration
public class TagConverterTest {

    @Autowired
    TagConverter tagConverter;

    @Autowired
    TagService tagService;

    @Test
    @WithMockUser(username = "user", authorities = {"USER"})
    public void test() {
        FacesContext facesContext = new FacesContext() {
            @Override
            public Application getApplication() {
                return null;
            }

            @Override
            public Iterator<String> getClientIdsWithMessages() {
                return null;
            }

            @Override
            public ExternalContext getExternalContext() {
                return null;
            }

            @Override
            public FacesMessage.Severity getMaximumSeverity() {
                return null;
            }

            @Override
            public Iterator<FacesMessage> getMessages() {
                return null;
            }

            @Override
            public Iterator<FacesMessage> getMessages(String s) {
                return null;
            }

            @Override
            public RenderKit getRenderKit() {
                return null;
            }

            @Override
            public boolean getRenderResponse() {
                return false;
            }

            @Override
            public boolean getResponseComplete() {
                return false;
            }

            @Override
            public ResponseStream getResponseStream() {
                return null;
            }

            @Override
            public void setResponseStream(ResponseStream responseStream) {

            }

            @Override
            public ResponseWriter getResponseWriter() {
                return null;
            }

            @Override
            public void setResponseWriter(ResponseWriter responseWriter) {

            }

            @Override
            public UIViewRoot getViewRoot() {
                return null;
            }

            @Override
            public void setViewRoot(UIViewRoot uiViewRoot) {

            }

            @Override
            public void addMessage(String s, FacesMessage facesMessage) {

            }

            @Override
            public void release() {

            }

            @Override
            public void renderResponse() {

            }

            @Override
            public void responseComplete() {

            }
        };

        UIComponent uiComponent = new UIComponent() {
            @Override
            public Map<String, Object> getAttributes() {
                return null;
            }

            @Override
            public String getClientId(FacesContext facesContext) {
                return null;
            }

            @Override
            public String getFamily() {
                return null;
            }

            @Override
            public String getId() {
                return null;
            }

            @Override
            public void setId(String s) {

            }

            @Override
            public UIComponent getParent() {
                return null;
            }

            @Override
            public void setParent(UIComponent uiComponent) {

            }

            @Override
            public boolean isRendered() {
                return false;
            }

            @Override
            public void setRendered(boolean b) {

            }

            @Override
            public String getRendererType() {
                return null;
            }

            @Override
            public void setRendererType(String s) {

            }

            @Override
            public boolean getRendersChildren() {
                return false;
            }

            @Override
            public List<UIComponent> getChildren() {
                return null;
            }

            @Override
            public int getChildCount() {
                return 0;
            }

            @Override
            public UIComponent findComponent(String s) {
                return null;
            }

            @Override
            public Map<String, UIComponent> getFacets() {
                return null;
            }

            @Override
            public UIComponent getFacet(String s) {
                return null;
            }

            @Override
            public Iterator<UIComponent> getFacetsAndChildren() {
                return null;
            }

            @Override
            public void broadcast(FacesEvent facesEvent) throws AbortProcessingException {

            }

            @Override
            public void decode(FacesContext facesContext) {

            }

            @Override
            public void encodeBegin(FacesContext facesContext) throws IOException {

            }

            @Override
            public void encodeChildren(FacesContext facesContext) throws IOException {

            }

            @Override
            public void encodeEnd(FacesContext facesContext) throws IOException {

            }

            @Override
            protected void addFacesListener(FacesListener facesListener) {

            }

            @Override
            protected FacesListener[] getFacesListeners(Class aClass) {
                return new FacesListener[0];
            }

            @Override
            protected void removeFacesListener(FacesListener facesListener) {

            }

            @Override
            public void queueEvent(FacesEvent facesEvent) {

            }

            @Override
            public void processRestoreState(FacesContext facesContext, Object o) {

            }

            @Override
            public void processDecodes(FacesContext facesContext) {

            }

            @Override
            public void processValidators(FacesContext facesContext) {

            }

            @Override
            public void processUpdates(FacesContext facesContext) {

            }

            @Override
            public Object processSaveState(FacesContext facesContext) {
                return null;
            }

            @Override
            protected FacesContext getFacesContext() {
                return null;
            }

            @Override
            protected Renderer getRenderer(FacesContext facesContext) {
                return null;
            }

            @Override
            public ValueBinding getValueBinding(String s) {
                return null;
            }

            @Override
            public void setValueBinding(String s, ValueBinding valueBinding) {

            }

            @Override
            public Object saveState(FacesContext facesContext) {
                return null;
            }

            @Override
            public void restoreState(FacesContext facesContext, Object o) {

            }

            @Override
            public boolean isTransient() {
                return false;
            }

            @Override
            public void setTransient(boolean b) {

            }
        };

        Tag loadedTag = tagService.loadTag("Alkohol");

        Assertions.assertEquals(loadedTag,tagConverter.getAsObject(facesContext,uiComponent,"Alkohol"));
        Assertions.assertNull(tagConverter.getAsObject(facesContext,uiComponent,"tag"));

        Tag newTag = new Tag();

        Assertions.assertEquals("Alkohol",tagConverter.getAsString(facesContext,uiComponent,loadedTag));
        Assertions.assertEquals("null",tagConverter.getAsString(facesContext,uiComponent,newTag));


    }
}
