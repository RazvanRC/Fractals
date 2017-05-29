README for the FractalCurves2 project.

-------------------------

COMPILE: To compile this java program from the command line use the command:

javac -sourcepath src -classpath @compile_classpath -d class @files_to_compile

This command should be run from the directory containing this readme. Some files 
should appear in the "class" directory.

-------------------------

RUN: To run the program use the one of the following commands:

java -classpath class DrawingDemo
java -classpath class KochSnowflakeDisplay
java -classpath class:freehep/* graphics.PolygonalPathDisplay

I'm not sure if the above works in Windows. If you have trouble, you could use the painfully long command:

java -classpath class:freehep/freehep-export-2.1.1.jar:freehep/freehep-graphics2d-2.1.1.jar:freehep/freehep-graphicsio-2.1.1.jar:freehep/freehep-graphicsio-emf-2.1.1.jar:freehep/freehep-graphicsio-java-2.1.1.jar:freehep/freehep-graphicsio-pdf-2.1.1.jar:freehep/freehep-graphicsio-ps-2.1.1.jar:freehep/freehep-graphicsio-svg-2.1.1.jar:freehep/freehep-graphicsio-swf-2.1.1.jar:freehep/freehep-graphicsio-tests-2.1.1.jar:freehep/freehep-io-2.0.2.jar:freehep/freehep-swing-2.0.3.jar:freehep/freehep-util-2.0.2.jar:freehep/freehep-xml-2.1.1.jar:freehep/jas-plotter-2.2.jar:freehep/jdom-1.0.jar:freehep/junit-3.8.2.jar:freehep/openide-lookup-1.9-patched-1.0.jar graphics.PolygonalPathDisplay

-------------------------

LICENSE: Most of these Java classes in the "src" directory are licensed under 
Version 2 of the GNU General Public License. Some of the classes are in the 
public domain. This is indicated in the headers of the files.

All Java classes are copyright W. Patrick Hooper, 2012.
