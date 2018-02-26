clean:
	mvn clean

coverage:
	mvn org.jacoco:jacoco-maven-plugin:prepare-agent install

sonar:
	mvn sonar:sonar
