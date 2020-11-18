CLASSES = src/domain/classes
CONTROLADORES = src/domain/controllers
VISTA = src/presentation
DATA = src/data
DRIVERS = src/domain/controllers/drivers

default: class

class:
	javac -d bin src/Main.java $(CLASSES)/*.java $(CONTROLADORES)/*.java $(VISTA)/*.java $(DATA)/*.java $(DRIVERS)/*.java

jar:
	jar -cvfm Kakuro.jar MANIFEST.MF -C bin/domain/classes .

#runjar:
#	java -jar Kakuro.jar

run:
	cd bin && java Main

run_driver_Cell_man:
	cd bin && java domain.controllers.drivers.DriverCell

run_driver_Cell_auto:
	cd bin && java domain.controllers.drivers.DriverCell < ../data/drivers/inputs/input_Cell.txt

clean:
	rm -rf bin/domain