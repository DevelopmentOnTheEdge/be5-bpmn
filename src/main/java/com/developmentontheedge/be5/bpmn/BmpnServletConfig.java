package com.developmentontheedge.be5.bpmn;

import com.developmentontheedge.be5.modules.core.CoreModule;
import com.developmentontheedge.be5.modules.core.CoreServletModule;
import com.developmentontheedge.be5.modules.core.services.RoleServiceImpl;
import com.developmentontheedge.be5.server.authentication.RoleService;
import com.developmentontheedge.be5.server.servlet.Be5ServletListener;
import com.developmentontheedge.be5.server.servlet.TemplateModule;
import com.developmentontheedge.be5.web.WebModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;


public class BmpnServletConfig extends Be5ServletListener
{
    @Override
    protected Injector getInjector()
    {
        return Guice.createInjector(getStage(), new BmpnAppModule());
    }

    private static class BmpnAppModule extends AbstractModule
    {
        @Override
        protected void configure()
        {
            install(new CoreModule());
            install(new CoreServletModule());
            install(new WebModule());
            install(new TemplateModule());

            bind(BpmnService.class).to(CamundaBpmnServiceImpl.class).in(Scopes.SINGLETON);
        }
    }
}
