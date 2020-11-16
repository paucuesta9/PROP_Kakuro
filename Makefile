CLASSES = src/domain/classes
CONTROLADORES = src/domain/controllers
VISTA = src/presentation

default: class

class:
	javac -d bin src/Main.java $(CLASSES)/*.java $(CONTROLADORES)/*.java $(VISTA)/*.java src/data/CtrlData.java

jar:
	jar -cvfm Kakuro.jar MANIFEST.MF -C bin/domain/classes .

#runjar:
#	java -jar Kakuro.jar

run:
	cd bin && java Main

clean:
	rm -rf bin/domain