CLASSES = src/domain/classes
CONTROLADORES = src/domain/controllers
VISTA = src/presentation
DATA = src/data
DRIVERS = src/domain/controllers/drivers
JUNIT = src/domain/controllers/junits

default: class

class:
	javac -d bin -sourcepath src src/Main.java $(CLASSES)/*.java $(CONTROLADORES)/*.java $(VISTA)/*.java $(DATA)/*.java $(DRIVERS)/*.java

jar:
	jar -cvfm Kakuro.jar MANIFEST.MF -C bin/ .

runjar:
	java -jar Kakuro.jar

run:
	java -cp bin Main

run_driver_WhiteCell_man:
	java -cp bin domain.controllers.drivers.DriverWhiteCell

run_driver_WhiteCell_auto:
	java -cp bin domain.controllers.drivers.DriverWhiteCell < data/juegos_prueba/Inputs/input_WhiteCell.txt

run_driver_BlackCell_man:
	java -cp bin domain.controllers.drivers.DriverBlackCell

run_driver_BlackCell_auto:
	java -cp bin domain.controllers.drivers.DriverBlackCell < data/juegos_prueba/Inputs/input_BlackCell.txt

run_driver_Cell_man:
	java -cp bin domain.controllers.drivers.DriverCell

run_driver_Cell_auto:
	java -cp bin domain.controllers.drivers.DriverCell < data/juegos_prueba/Inputs/input_Cell.txt

run_driver_Kakuro_man:
	java -cp bin domain.controllers.drivers.DriverKakuro

run_driver_Kakuro_auto:
	java -cp bin domain.controllers.drivers.DriverKakuro < data/juegos_prueba/Inputs/input_Kakuro.txt

run_driver_CtrlDomain_man:
	java -cp bin domain.controllers.drivers.DriverCtrlDomain

run_driver_CtrlDomain_auto:
	java -cp bin domain.controllers.drivers.DriverCtrlDomain < data/juegos_prueba/Inputs/input_CtrlDomain.txt

run_driver_CtrlGenerate_man:
	java -cp bin domain.controllers.drivers.DriverCtrlGenerate

run_driver_CtrlGenerate_auto:
	java -cp bin domain.controllers.drivers.DriverCtrlGenerate < data/juegos_prueba/Inputs/input_CtrlGenerate.txt

run_driver_CtrlPlay_man:
	java -cp bin domain.controllers.drivers.DriverCtrlPlay

run_driver_CtrlPlay_auto:
	java -cp bin domain.controllers.drivers.DriverCtrlPlay < data/juegos_prueba/Inputs/input_CtrlPlay.txt

run_driver_CtrlResolve_man:
	java -cp bin domain.controllers.drivers.DriverCtrlResolve

run_driver_CtrlResolve_auto:
	java -cp bin domain.controllers.drivers.DriverCtrlResolve < data/juegos_prueba/Inputs/input_CtrlResolve.txt

run_driver_CtrlValidate_man:
	java -cp bin domain.controllers.drivers.DriverCtrlValidate

run_driver_CtrlValidate_auto:
	java -cp bin domain.controllers.drivers.DriverCtrlValidate < data/juegos_prueba/Inputs/input_CtrlValidate.txt

run_junit:
	java -cp bin domain.controllers.junits.KakuroTest

clean:
	rm -rf bin