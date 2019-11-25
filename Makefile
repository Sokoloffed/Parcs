all: run

clean:
	rm -f out/RhoPollardP.jar out/PollardRho.jar

out/RhoPollardP.jar: out/parcs.jar src/RhoPollardP.java
	@javac -cp out/parcs.jar src/RhoPollardP.java
	@jar cf out/RhoPollardP.jar -C src RhoPollardP.class 
	@rm -f src/RhoPollardP.class  

out/PollardRho.jar: out/parcs.jar src/PollardRho.java
	@javac -cp out/parcs.jar src/PollardRho.java 
	@jar cf out/PollardRho.jar -C src PollardRho.class 
	@rm -f src/PollardRho.class 

build: out/RhoPollardP.jar out/PollardRho.jar

run: out/RhoPollardP.jar out/PollardRho.jar
	@cd out && java -cp 'parcs.jar:RhoPollardP.jar' RhoPollardP
