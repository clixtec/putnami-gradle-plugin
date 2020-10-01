/**
 * This file is part of pwt.
 *
 * pwt is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * pwt is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with pwt. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package fr.putnami.gwt.gradle.task;

import org.gradle.api.plugins.WarPlugin;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.bundling.War;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

import fr.putnami.gwt.gradle.action.JavaAction;
import fr.putnami.gwt.gradle.extension.PutnamiExtension;
import fr.putnami.gwt.gradle.helper.JettyServerBuilder;
import fr.putnami.gwt.gradle.util.ResourceUtils;

public class GwtRunTask extends AbstractTask {

	public static final String NAME = "gwtRun";

	private File jettyConf;

	public GwtRunTask() {
		setDescription("Run jetty with the GW the GWT modules");

		dependsOn(WarPlugin.WAR_TASK_NAME);
	}

	@TaskAction
	public void exec() throws Exception {
		War warTask = (War) getProject().getTasks().getByName("war");
		jettyConf = new File(getProject().getBuildDir(), "putnami/conf/jetty-run-conf.xml");
		Map<String, String> model = new HashMap<String, String>();
		model.put("__WAR_FILE__", warTask.getArchivePath().getAbsolutePath());
		ResourceUtils.copy("/stub.jetty-conf.xml", jettyConf, model);
		JavaAction jetty = execJetty();
		jetty.join();
	}

	private JavaAction execJetty() {
		PutnamiExtension putnami = getProject().getExtensions().getByType(PutnamiExtension.class);
		JettyServerBuilder jettyBuilder = new JettyServerBuilder();
		jettyBuilder.configure(getProject(), putnami.getJetty(), jettyConf);
		JavaAction jetty = jettyBuilder.buildJavaAction();
		jetty.execute(this);
		return jetty;
	}

}
