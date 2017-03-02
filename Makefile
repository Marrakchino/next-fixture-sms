S_PATH= -sourcepath
C_PATH= -classpath
SOURCES= src/nextfixturesms

all:
	Main

Fixture.class:
	javac -d bin $$(C_PATH) "bin/*" $(S_PATH) $(SOURCES)/Fixture.java

Parser.class:
	javac -d bin $(SOURCES)/Parser.java

Main.class: Fixture.class Parser.class
	javac -d bin $(C_PATH) "bin/*" $(S_PATH) $(SOURCES) $(SOURCES)/Main.java

Main: Main.class
	java $(C_PATH) bin:bin/*.jar Main

.PHONY: clean
clean:
	rm -rf bin/*.class
