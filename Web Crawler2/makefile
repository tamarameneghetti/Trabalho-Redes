JFLAGS = -g
JC = javac
JVM = java
FILE = testeme

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	WebCrawler.java\
	Crawler.java\

MAIN = WebCrawler

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class