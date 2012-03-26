/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package pur.gwtplatform.samples.modules;

import pur.gwtplatform.samples.presenter.DeleteDialogPresenter;
import pur.gwtplatform.samples.presenter.DialogPresenter;
import pur.gwtplatform.samples.presenter.InsertPresenter;
import pur.gwtplatform.samples.presenter.MainPresenter;
import pur.gwtplatform.samples.views.DialogView;
import pur.gwtplatform.samples.views.IDeleteDialogView;
import pur.gwtplatform.samples.views.IDialogView;
import pur.gwtplatform.samples.views.IMainView;
import pur.gwtplatform.samples.views.IInsertView;
import pur.gwtplatform.samples.views.InsertView;
import pur.gwtplatform.samples.views.MainView;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import pur.gwtplatform.samples.views.DeleteDialogView;

public class Module extends AbstractPresenterModule {

	@Override
	protected void configure() {
		// Default implementation of standard resources
		install(new DefaultModule(PlaceManager.class));

		// Presenters
		bindPresenter(MainPresenter.class, IMainView.class, MainView.class, MainPresenter.MyProxy.class);
		bindPresenter(InsertPresenter.class, IInsertView.class, InsertView.class, InsertPresenter.MyProxy.class);
		// bind des popups
		bindSingletonPresenterWidget(DialogPresenter.class, IDialogView.class, DialogView.class);
		bindSingletonPresenterWidget(DeleteDialogPresenter.class, IDeleteDialogView.class, DeleteDialogView.class);
		
	}
}