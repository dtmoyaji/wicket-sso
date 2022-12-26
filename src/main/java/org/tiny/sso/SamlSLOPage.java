/*
 * Copyright 2022 DtmOyaji.
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
package org.tiny.sso;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * SAML Single Log Out Page.
 *
 * @author DtmOyaji
 */
public class SamlSLOPage extends WebPage {

    Roles myRole;

    public SamlSLOPage(final PageParameters params) {
        super(params);

        SamlProcess process = new SamlProcess(this, SamlProcess.MODE_CHECKLOGIN);
        if (process.getStatus() == SamlAuthInfo.STATUS_AUTHENTICATED) {
            SamlSession authSession = (SamlSession) this.getSession();

            String responseXml = authSession.getSamlAuthInfo().getNameId();
            responseXml = authSession.getSamlAuthInfo().getSessionIndex();

            new SamlProcess(this, SamlProcess.MODE_LOGOUT);

        } else {
            this.setResponsePage(this.getApplication().getHomePage());

        }

    }
}
