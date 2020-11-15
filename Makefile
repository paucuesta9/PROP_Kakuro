CLASSES = src/domain/classes
CONTROLADORES = src/domain/controllers
VISTA = src/presentation

default: class

class:
	javac -d bin $(CLASSES)/*.java $(CONTROLADORES)/*.java $(VISTA)/*.java src/data/CtrlData.java

jar:
	jar cvf Kakuro.jar -C bin/domain/classes .

runjar:
	java -jar Kakuro.jar

run:
	java -cp src/data/CtrlData.java  domain.classes.Main

clean:
	rm -rf bin/domain