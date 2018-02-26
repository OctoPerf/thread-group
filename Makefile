clean:
	mvn clean

test-coverage:
	mvn org.jacoco:jacoco-maven-plugin:prepare-agent install

sonar:
	mvn sonar:sonar
