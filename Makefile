all: run

clean:
	rm -f out/RhoPollardP.jar out/PollardRho.jar

out/RhoPollardP.jar: out/parcs.jar src/RhoPollardP.java src/Result.java
	@javac -cp out/parcs.jar src/RhoPollardP.java src/Result.java
	@jar cf out/RhoPollardP.jar -C src RhoPollardP.class -C src Result.class
	@rm -f src/RhoPollardP.class src/Result.class

out/PollardRho.jar: out/parcs.jar src/PollardRho.java src/Result.java
	@javac -cp out/parcs.jar src/PollardRho.java src/Result.java
	@jar cf out/PollardRho.jar -C src PollardRho.class -C src Result.class
	@rm -f src/PollardRho.class src/Result.class

build: out/RhoPollardP.jar out/PollardRho.jar

run: out/RhoPollardP.jar out/PollardRho.jar
	@cd out && java -cp 'parcs.jar:RhoPollardP.jar' RhoPollardP
