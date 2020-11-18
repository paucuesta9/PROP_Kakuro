CLASSES = src/domain/classes
CONTROLADORES = src/domain/controllers
VISTA = src/presentation
DATA = src/data
DRIVERS = src/domain/controllers/drivers
JUNIT = src/domain/controllers/junit

default: class

class:
	javac -d bin src/Main.java $(CLASSES)/*.java $(CONTROLADORES)/*.java $(VISTA)/*.java $(DATA)/*.java $(DRIVERS)/*.java $(JUNIT)/*.java

jar:
	jar -cvfm Kakuro.jar MANIFEST.MF -C bin/domain/classes .

#runjar:
#	java -jar Kakuro.jar

run:
	cd bin && java Main

run_driver_WhiteCell_man:
	cd bin && java domain.controllers.drivers.DriverWhiteCell

run_driver_WhiteCell_auto:
	cd bin && java domain.controllers.drivers.DriverWhiteCell < ../src/domain/controllers/drivers/Inputs/input_WhiteCell.txt

run_driver_BlackCell_man:
	cd bin && java domain.controllers.drivers.DriverBlackCell

run_driver_BlackCell_auto:
	cd bin && java domain.controllers.drivers.DriverBlackCell < ../src/domain/controllers/drivers/Inputs/input_BlackCell.txt

run_driver_Cell_man:
	cd bin && java domain.controllers.drivers.DriverCell

run_driver_Cell_auto:
	cd bin && java domain.controllers.drivers.DriverCell < ../src/domain/controllers/drivers/Inputs/input_Cell.txt

run_driver_Kakuro_man:
	cd bin && java domain.controllers.drivers.DriverKakuro

run_driver_Kakuro_auto:
	cd bin && java domain.controllers.drivers.DriverKakuro < ../src/domain/controllers/drivers/Inputs/input_Kakuro.txt

run_driver_CtrlDomain_man:
	cd bin && java domain.controllers.drivers.DriverCtrlDomain

run_driver_CtrlDomain_auto:
	cd bin && java domain.controllers.drivers.DriverCtrlDomain < ../src/domain/controllers/drivers/Inputs/input_CtrlDomain.txt

run_driver_CtrlGenerate_man:
	cd bin && java domain.controllers.drivers.DriverCtrlGenerate

run_driver_CtrlGenerate_auto:
	cd bin && java domain.controllers.drivers.DriverCtrlGenerate < ../src/domain/controllers/drivers/Inputs/input_CtrlGenerate.txt

run_driver_CtrlPlay_man:
	cd bin && java domain.controllers.drivers.DriverCtrlPlay

run_driver_CtrlPlay_auto:
	cd bin && java domain.controllers.drivers.DriverCtrlPlay < ../src/domain/controllers/drivers/Inputs/input_CtrlPlay.txt

run_driver_CtrlResolve_man:
	cd bin && java domain.controllers.drivers.DriverCtrlResolve

run_driver_CtrlResolve_auto:
	cd bin && java domain.controllers.drivers.DriverCtrlResolve < ../src/domain/controllers/drivers/Inputs/input_CtrlResolve.txt

run_driver_CtrlValidate_man:
	cd bin && java domain.controllers.drivers.DriverCtrlValidate

run_driver_CtrlValidate_auto:
	cd bin && java domain.controllers.drivers.DriverCtrlValidate < ../src/domain/controllers/drivers/Inputs/input_CtrlValidate.txt

run_junit:
	cd bin && java domain.controllers.junits.KakuroTest

clean:
	rm -rf bin/domain