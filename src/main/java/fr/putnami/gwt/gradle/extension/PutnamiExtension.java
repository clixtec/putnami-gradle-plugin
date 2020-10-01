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
package fr.putnami.gwt.gradle.extension;

import java.util.ArrayList;

import org.gradle.util.ConfigureUtil;

import java.util.Arrays;
import java.util.List;

import groovy.lang.Closure;

public class PutnamiExtension {
	public static final String PWT_EXTENSION = "putnami";

	private String gwtVersion = "2.7.0";
	private boolean gwtServletLib = false;
	private boolean gwtElementalLib = false;
	private boolean googlePluginEclipse = false;
	private String jettyVersion = "9.2.7.v20150116";
	/**
	 * Specifies Java source level.
	 */
	private String sourceLevel;

	/**
	 * GWT Module to compile.
	 */
	private final List<String> module = new ArrayList<>();

	private CompilerOption compile = new CompilerOption();
	private DevOption dev = new DevOption();
	private JettyOption jetty = new JettyOption();

	public String getGwtVersion() {
		return gwtVersion;
	}

	public void setGwtVersion(String gwtVersion) {
		this.gwtVersion = gwtVersion;
	}

	public String getJettyVersion() {
		return jettyVersion;
	}

	public void setJettyVersion(String jettyVersion) {
		this.jettyVersion = jettyVersion;
	}

	public boolean isGwtServletLib() {
		return gwtServletLib;
	}

	public void setGwtServletLib(boolean gwtServletLib) {
		this.gwtServletLib = gwtServletLib;
	}

	public boolean isGwtElementalLib() {
		return gwtElementalLib;
	}

	public void setGwtElementalLib(boolean gwtElementalLib) {
		this.gwtElementalLib = gwtElementalLib;
	}

	public boolean isGooglePluginEclipse() {
		return googlePluginEclipse;
	}

	public void setGooglePluginEclipse(boolean googlePluginEclipse) {
		this.googlePluginEclipse = googlePluginEclipse;
	}

	public DevOption getDev() {
		return dev;
	}

	public void setDev(DevOption dev) {
		this.dev = dev;
	}

	public PutnamiExtension dev(Closure<DevOption> c) {
		ConfigureUtil.configure(c, dev);
		return this;
	}

	public CompilerOption getCompile() {
		return compile;
	}

	public void setCompile(CompilerOption compile) {
		this.compile = compile;
	}

	public PutnamiExtension compile(Closure<CompilerOption> c) {
		ConfigureUtil.configure(c, compile);
		return this;
	}

	public JettyOption getJetty() {
		return jetty;
	}

	public void setJetty(JettyOption jetty) {
		this.jetty = jetty;
	}

	public PutnamiExtension jetty(Closure<JettyOption> c) {
		ConfigureUtil.configure(c, jetty);
		return this;
	}

	public String getSourceLevel() {
		return sourceLevel;
	}

	public void setSourceLevel(String sourceLevel) {
		this.sourceLevel = sourceLevel;
	}

	public List<String> getModule() {
		return module;
	}

	public void module(String... modules) {
		this.module.addAll(Arrays.asList(modules));
	}
}
