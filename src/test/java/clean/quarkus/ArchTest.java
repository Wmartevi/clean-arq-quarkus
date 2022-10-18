package clean.quarkus;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@QuarkusTest
class ArchTest {

    JavaClasses importedClasses = new ClassFileImporter().importPackages("clean.quarkus");

    @Test
    public void testDomainShouldNotAccessAnyOtherLayer() {
        ArchRule rule = noClasses().that()
                .resideInAPackage("..domain..")
                .should().accessClassesThat()
                .resideInAnyPackage("..infra..", "..presentation..", "..data..", "..main..");

        rule.check(importedClasses);
    }

    @Test
    public void testInfraShouldOnlyBeAccessedByDataAndMain() {
        ArchRule rule = layeredArchitecture()
                .layer("Infra").definedBy("clean.quarkus.infra..")
                .layer("Main").definedBy("clean.quarkus.main..")
                .layer("Domain").definedBy("clean.quarkus.domain..")
                .layer("Presentation").definedBy("clean.quarkus.presentation..")
                .layer("Data").definedBy("clean.quarkus.data..")
                .whereLayer("Infra").mayOnlyBeAccessedByLayers("Main");

        rule.check(importedClasses);
    }

    @Test
    public void testDataShouldOnlyBeAccessedByInfraAndMain() {
        ArchRule rule = layeredArchitecture()
                .layer("Infra").definedBy("clean.quarkus.infra..")
                .layer("Main").definedBy("clean.quarkus.main..")
                .layer("Domain").definedBy("clean.quarkus.domain..")
                .layer("Presentation").definedBy("clean.quarkus.presentation..")
                .layer("Data").definedBy("clean.quarkus.data..")
                .whereLayer("Data").mayOnlyBeAccessedByLayers("Main", "Infra");

        rule.check(importedClasses);
    }

    @Test
    public void testMainShouldOnlyBeAccessedByPresentation() {
        ArchRule rule = layeredArchitecture()
                .layer("Infra").definedBy("clean.quarkus.infra..")
                .layer("Main").definedBy("clean.quarkus.main..")
                .layer("Domain").definedBy("clean.quarkus.domain..")
                .layer("Presentation").definedBy("clean.quarkus.presentation..")
                .layer("Data").definedBy("clean.quarkus.data..")
                .whereLayer("Main").mayOnlyBeAccessedByLayers("Presentation");

        rule.check(importedClasses);
    }

    @Test
    public void testPresentationShouldNotBeAccessedByAnyLayer() {
        ArchRule rule = layeredArchitecture()
                .layer("Infra").definedBy("clean.quarkus.infra..")
                .layer("Main").definedBy("clean.quarkus.main..")
                .layer("Domain").definedBy("clean.quarkus.domain..")
                .layer("Presentation").definedBy("clean.quarkus.presentation..")
                .layer("Data").definedBy("clean.quarkus.data..")
                .whereLayer("Presentation").mayNotBeAccessedByAnyLayer();

        rule.check(importedClasses);
    }
}
