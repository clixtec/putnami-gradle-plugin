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
package fr.putnami.gwt.gradle.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public final class ResourceUtils {
	private ResourceUtils() {
	}

	public static File ensureDir(File parent, String path) {
		return ensureDir(new File(parent, path));
	}

	public static File ensureDir(File dir) {
		if (dir != null) {
			dir.mkdirs();
		}
		return dir;
	}

	public static File copy(String resourcePath, File targetDir, String targetName)
		throws IOException {
		return copy(resourcePath, targetDir, targetName, null);
	}

	public static File copy(String resourcePath, File targetDir, String targetName, Map<String, String> model)
		throws IOException {
		return copy(resourcePath, new File(targetDir, targetName), model);
	}

	public static File copy(String resourcePath, File target, Map<String, String> model)
		throws IOException {

		InputStream input = ResourceUtils.class.getResource(resourcePath).openStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		int b = input.read();
		while(b >= 0){
			out.write(b);
			b = input.read();
		}

		String template = new String(out.toByteArray());
		// Resources.toString(url, Charsets.UTF_8);

		if (model != null) {
			for (Map.Entry<String, String> entry : model.entrySet()) {
				template = template.replace(entry.getKey(), entry.getValue());
			}
		}

		if (target.getParentFile() != null) {
			target.getParentFile().mkdirs();
		}
		FileWriter writer = new FileWriter(target);
		writer.write(template);
		writer.close();
		return target;
	}

	public static void copyDirectory(File source, File target) throws IOException {
		if (source == null) {
			return;
		}
		if (!target.exists()) {
			target.mkdirs();
		}

		String[] files = source.list();
		if (files != null) {
			for (String fileName : files) {
				File s = new File(source, fileName);
				File t = new File(target, fileName);
				if (s.isDirectory()) {
					copyDirectory(s, t);
				} else {
					copy(s, t);
				}
			}
		}
	}

	public static void copy(File source, File target) throws IOException {
		try (
			InputStream in = new FileInputStream(source);
			OutputStream out = new FileOutputStream(target)) {
			byte[] buf = new byte[1024];
			int length;
			while ((length = in.read(buf)) > 0) {
				out.write(buf, 0, length);
			}
		}
	}

	public static boolean deleteDirectory(File directory) {
		if (directory.exists()) {
			File[] files = directory.listFiles();
			if (null != files) {
				for (File file : files) {
					if (file.isDirectory()) {
						deleteDirectory(file);
					} else {
						file.delete();
					}
				}
			}
		}
		return directory.delete();
	}
}
