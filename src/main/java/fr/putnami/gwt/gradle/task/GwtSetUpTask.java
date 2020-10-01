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

import org.gradle.api.Project;
import org.gradle.api.internal.ConventionMapping;
import org.gradle.api.internal.IConventionAware;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.Callable;

import fr.putnami.gwt.gradle.extension.PutnamiExtension;
import fr.putnami.gwt.gradle.util.ResourceUtils;

public class GwtSetUpTask extends AbstractTask {

	public static final String NAME = "gwtSetUp";

	private List<String> modules;

	public GwtSetUpTask() {
		setDescription("Set up the GWT project from a skeleton");
	}

	@TaskAction
	public void exec() throws Exception {

		PutnamiExtension putnami = getProject().getExtensions().getByType(PutnamiExtension.class);
		File projectDir = getProject().getProjectDir();
		File srcMainJava = ResourceUtils.ensureDir(new File(projectDir, "src/main/java"));
		File srcMainWebapp = ResourceUtils.ensureDir(new File(projectDir, "src/main/webapp"));

		for (String module : putnami.getModule()) {
			String moduleName = module.substring(module.lastIndexOf('.') + 1);
			String packageName = module.substring(0, module.lastIndexOf('.'));
			String packagePath = packageName.replaceAll("\\.", "/");

			File moduleDir = ResourceUtils.ensureDir(new File(srcMainJava, packagePath));
			File clientDir = ResourceUtils.ensureDir(new File(moduleDir, "client"));

			Map<String, String> model = new HashMap<String, String>();
			model.put("__APP_NAME__", moduleName);
			model.put("__PKG_NAME__", packageName);

			ResourceUtils.copy("/skeleton/gwt.xml.txt", new File(moduleDir, moduleName + ".gwt.xml"), model);
			ResourceUtils.copy("/skeleton/entryPoint.java.txt", new File(clientDir, moduleName + ".java"), model);
			ResourceUtils.copy("/skeleton/entryPoint.ui.xml.txt", new File(clientDir, moduleName + ".ui.xml"), model);
			ResourceUtils.copy("/skeleton/index.html.txt", new File(srcMainWebapp, "index.html"), model);
		}
	}

	public void configure(final PutnamiExtension extension) {
		ConventionMapping mapping = ((IConventionAware) this).getConventionMapping();

		mapping.map("modules", new Callable<List<String>>() {
			@Override
			public List<String> call()  {
				return extension.getModule();
			}
		});
	}

	public List<String> getModules() {
		return modules;
	}

	public static boolean isEnable(final Project project, final PutnamiExtension extension) {
		String mainModule = null;
		if (extension.getModule() != null &&
			extension.getModule().size() > 0) {
			mainModule = extension.getModule().get(0);
		}
		if (mainModule != null) {
			String moduleFilePath = "src/main/java/" + mainModule.replaceAll("\\.", "/") + ".gwt.xml";
			File moduleFile = new File(project.getProjectDir(), moduleFilePath);
			return !moduleFile.exists();
		}
		return false;
	}
}
