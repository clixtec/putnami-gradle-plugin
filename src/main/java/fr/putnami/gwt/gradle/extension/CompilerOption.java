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

import org.gradle.api.Project;

import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * GWT Compiler options.
 */
public class CompilerOption extends JavaOption {

	/**
	 * The compiler's working directory for internal use (must be writeable; defaults to a system
	 * buil/work).
	 */
	private File workDir;
	/**
	 * The directory into which deployable output files will be written.
	 */
	private File war;
	/**
	 * The directory into which deployable but not servable output files will be written (defaults to
	 * 'WEB-INF/deploy' under the -war directory/jar, and may be the same as the -extra
	 * directory/jar).
	 */
	private File deploy;
	/**
	 * Debugging: causes normally-transient generated types to be saved in the specified directory.
	 */
	private File gen;
	/**
	 * The directory into which extra files, not intended for deployment, will be written.
	 */
	private File extra;
	/**
	 * Overrides where source files useful to debuggers will be written. Default: saved with extras.
	 */
	private File saveSourceOutput;
	/**
	 * Specifies a file into which detailed missing dependency information will be written.
	 */
	private File missingDepsFile;
	/**
	 * The level of logging detail.
	 */
	private LogLevel logLevel;
	/**
	 * Shown all compile errors.
	 */
	private Boolean strict;
	/**
	 * Compile a report that tells the "Story of Your Compile".
	 */
	private Boolean compileReport;
	/**
	 * Compile quickly with minimal optimizations.
	 */
	private Boolean draftCompile;
	/**
	 * Include assert statements in compiled output.
	 */
	private Boolean checkAssertions;
	/**
	 * Script output style.
	 */
	private CodeStyle style;
	/**
	 * Sets the optimization level used by the compiler. 0=none 9=maximum.
	 */
	private Integer optimize;
	/**
	 * Whether to show warnings during monolithic compiles for overlapping source inclusion.
	 */
	private Boolean overlappingSourceWarnings;
	/**
	 * Enables saving source code needed by debuggers. Also see -debugDir.
	 */
	private Boolean saveSource;
	/**
	 * Fail compilation if any input file contains an error.
	 */
	private Boolean failOnError;
	/**
	 * Specifies Java source level.
	 */
	private String sourceLevel;
	/**
	 * The number of local workers to use when compiling permutations.
	 */
	private Integer localWorkers;
	/**
	 * The number of local workers to use when compiling permutations.
	 */
	private Integer localWorkersMem = 2048;
	/**
	 * Compiles faster by reusing data from the previous compile.
	 */
	private Boolean incremental;
	/**
	 * Emit extra information allow chrome dev tools to display Java identifiers in many places
	 * instead of JavaScript functions.
	 */
	private MethodNameDisplayMode methodNameDisplayMode;
	/**
	 * EXPERIMENTAL: Avoid adding implicit dependencies on "client" and "public" for modules that
	 * don't define any dependencies.
	 */
	private Boolean enforceStrictResources;
	/**
	 * EXPERIMENTAL: Insert run-time checking of cast operations.
	 */
	private Boolean checkCasts;
	/**
	 * EXPERIMENTAL: Include metadata for some java.lang.Class methods (e.g. getName()).
	 */
	private Boolean classMetadata;
	/**
	 * Specifies JsInterop mode, either NONE, JS, or CLOSURE (till GWT 2.7.x ).
	 */
	private JsInteropMode jsInteropMode;
	/**
	 * Generate and export JsInterop (since GWT 2.8).
	 */
	private Boolean generateJsInteropExports;

	/**
	 * GWT extra args, can be used to experiment arguments.
	 */
	private final List<String> extraArgs = new ArrayList<>();

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = LogLevel.valueOf(logLevel);
	}

	public Boolean getStrict() {
		return strict;
	}

	public void setStrict(Boolean strict) {
		this.strict = strict;
	}

	public void setStrict(String strict) {
		this.strict = Boolean.valueOf(strict);
	}

	public File getWorkDir() {
		return workDir;
	}

	public void setWorkDir(File workDir) {
		this.workDir = workDir;
	}

	public void setWorkDir(String workDir) {
		this.workDir = new File(workDir);
	}

	public Boolean getCompileReport() {
		return compileReport;
	}

	public void setCompileReport(Boolean compileReport) {
		this.compileReport = compileReport;
	}

	public void setCompileReport(String compileReport) {
		this.compileReport = Boolean.parseBoolean(compileReport);
	}

	public Boolean getDraftCompile() {
		return draftCompile;
	}

	public void setDraftCompile(Boolean draftCompile) {
		this.draftCompile = draftCompile;
	}

	public void setDraftCompile(String draftCompile) {
		this.draftCompile = Boolean.parseBoolean(draftCompile);
	}

	public Boolean getCheckAssertions() {
		return checkAssertions;
	}

	public void setCheckAssertions(Boolean checkAssertions) {
		this.checkAssertions = checkAssertions;
	}

	public void setCheckAssertions(String checkAssertions) {
		this.checkAssertions = Boolean.valueOf(checkAssertions);
	}

	public File getGen() {
		return gen;
	}

	public void setGen(File gen) {
		this.gen = gen;
	}

	public void setGen(String gen) {
		this.gen = new File(gen);
	}

	public File getMissingDepsFile() {
		return missingDepsFile;
	}

	public void setMissingDepsFile(String missingDepsFile) {
		this.missingDepsFile = new File(missingDepsFile);
	}

	public Integer getOptimize() {
		return optimize;
	}

	public void setOptimize(Integer optimize) {
		this.optimize = optimize;
	}

	public void setOptimize(String optimize) {
		this.optimize = Integer.valueOf(optimize);
	}

	public Boolean getOverlappingSourceWarnings() {
		return overlappingSourceWarnings;
	}

	public void setOverlappingSourceWarnings(Boolean overlappingSourceWarnings) {
		this.overlappingSourceWarnings = overlappingSourceWarnings;
	}

	public Boolean getSaveSource() {
		return saveSource;
	}

	public void setSaveSource(Boolean saveSource) {
		this.saveSource = saveSource;
	}

	public void setSaveSource(String saveSource) {
		this.saveSource = Boolean.parseBoolean(saveSource);
	}

	public CodeStyle getStyle() {
		return style;
	}

	public void setStyle(CodeStyle style) {
		this.style = style;
	}

	public void setStyle(String style) {
		this.style = CodeStyle.valueOf(style);
	}

	public Boolean getFailOnError() {
		return failOnError;
	}

	public void setFailOnError(Boolean failOnError) {
		this.failOnError = failOnError;
	}

	public void setFailOnError(String failOnError) {
		this.failOnError = Boolean.parseBoolean(failOnError);
	}

	public String getSourceLevel() {
		return sourceLevel;
	}

	public void setSourceLevel(String sourceLevel) {
		this.sourceLevel = sourceLevel;
	}

	public Integer getLocalWorkers() {
		return localWorkers;
	}

	public void setLocalWorkers(Integer localWorkers) {
		this.localWorkers = localWorkers;
	}

	public void setLocalWorkers(String localWorkers) {
		this.localWorkers = Integer.valueOf(localWorkers);
	}

	public Integer getLocalWorkersMem() {
		return localWorkersMem;
	}

	public void setLocalWorkersMem(Integer localWorkersMem) {
		this.localWorkersMem = localWorkersMem;
	}

	public void setLocalWorkersMem(String localWorkersMem) {
		this.localWorkersMem = Integer.valueOf(localWorkersMem);
	}

	public Boolean getIncremental() {
		return incremental;
	}

	public void setIncremental(Boolean incremental) {
		this.incremental = incremental;
	}

	public void setIncremental(String incremental) {
		this.incremental = Boolean.parseBoolean(incremental);
	}

	public File getWar() {
		return war;
	}

	public void setWar(String war) {
		this.war = new File(war);
	}

	public File getDeploy() {
		return deploy;
	}

	public void setDeploy(String deploy) {
		this.deploy = new File(deploy);
	}

	public File getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = new File(extra);
	}

	public File getSaveSourceOutput() {
		return saveSourceOutput;
	}

	public void setSaveSourceOutput(String saveSourceOutput) {
		this.saveSourceOutput = new File(saveSourceOutput);
	}

	public MethodNameDisplayMode getMethodNameDisplayMode() {
		return methodNameDisplayMode;
	}

	public void setMethodNameDisplayMode(MethodNameDisplayMode methodNameDisplayMode) {
		this.methodNameDisplayMode = methodNameDisplayMode;
	}

	public void setMethodNameDisplayMode(String methodNameDisplayMode) {
		this.methodNameDisplayMode = MethodNameDisplayMode.valueOf(methodNameDisplayMode);
	}

	public Boolean getEnforceStrictResources() {
		return enforceStrictResources;
	}

	public void setEnforceStrictResources(Boolean enforceStrictResources) {
		this.enforceStrictResources = enforceStrictResources;
	}

	public void setEnforceStrictResources(String enforceStrictResources) {
		this.enforceStrictResources = Boolean.parseBoolean(enforceStrictResources);
	}

	public Boolean getCheckCasts() {
		return checkCasts;
	}

	public void setCheckCasts(Boolean checkCasts) {
		this.checkCasts = checkCasts;
	}

	public void setCheckCasts(String checkCasts) {
		this.checkCasts = Boolean.parseBoolean(checkCasts);
	}

	public Boolean getClassMetadata() {
		return classMetadata;
	}

	public void setClassMetadata(Boolean classMetadata) {
		this.classMetadata = classMetadata;
	}

	public void setClassMetadata(String classMetadata) {
		this.classMetadata = Boolean.parseBoolean(classMetadata);
	}

	public JsInteropMode getJsInteropMode() {
		return jsInteropMode;
	}

	public void setJsInteropMode(String jsInteropMode) {
		this.jsInteropMode = JsInteropMode.valueOf(jsInteropMode);
	}

	public void setJsInteropMode(JsInteropMode jsInteropMode) {
		this.jsInteropMode = jsInteropMode;
	}

	public Boolean getGenerateJsInteropExports() {
		return generateJsInteropExports;
	}

	public void setGenerateJsInteropExports(Boolean generateJsInteropExports) {
		this.generateJsInteropExports = generateJsInteropExports;
	}

	public void setGenerateJsInteropExports(String generateJsInteropExports) {
		this.generateJsInteropExports = Boolean.parseBoolean(generateJsInteropExports);
	}

	public List<String> getExtraArgs() {
		return extraArgs;
	}

	public void setExtraArgs(String... extraArgs) {
		this.extraArgs.addAll(Arrays.asList(extraArgs));
	}

	public void init(Project project) {
		final File buildDir = new File(project.getBuildDir(), "putnami");

		this.war = new File(buildDir, "out");
		this.workDir = new File(buildDir, "work");
		this.gen = new File(buildDir, "extra/gen");
		this.deploy = new File(buildDir, "extra/deploy");
		this.extra = new File(buildDir, "extra");
		this.saveSourceOutput = new File(buildDir, "extra/source");
		this.missingDepsFile = new File(buildDir, "extra/missingDepsFile");
	}

}
