package ctflearn.tests;

import ctflearn.models.Challenge;
import ctflearn.pages.CreateChallengePage;
import ctflearn.pages.MyChallengesPage;
import org.testng.annotations.Test;

import static ctflearn.enums.ChallengeCategory.*;
import static org.testng.Assert.assertTrue;

public class ChallengeCreationTest extends BaseTest {
    private static final String ctflearnFlag = "CTFlearn{4m_1_4_r3al_h4ck3r_y3t}";
    private CreateChallengePage createChallengePage;
    private MyChallengesPage myChallengesPage;
    private static final String[] categories = {WEB, FORENSICS, BINARY, REVERSE_ENGINEERING, CRYPTOGRAPHY, PROGRAMMING, MISCELLANEOUS};
    private static final int[] points = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

    @Test
    public void test_createChallenge() {
        Challenge challenge = new Challenge();
        challenge.setTitle(faker.name().title());
        challenge.setFlag(ctflearnFlag);
        challenge.setDescription(faker.lorem().paragraph());
        challenge.setCategory(categories[rand.nextInt(categories.length)]);
        challenge.setPoints(points[rand.nextInt(points.length)]);
        challenge.setHowWeSolveThis(faker.lorem().sentence());

        createChallengePage = topMenu.navigateToNewChallengePage();
        assertTrue(createChallengePage.isCreateChallengeFormDisplayed(), "Create new challenges form is not displayed correctly");
        createChallengePage.createChallenge(challenge);
        myChallengesPage = topMenu.navigateToMyChallengesPage();
        assertTrue(myChallengesPage.isMyChallengePageDisplay(), "My Challenges page is not displayed properly");
        assertTrue(myChallengesPage.cardExist(challenge.getTitle()), "Card " + challenge.getTitle() + " does not exist");
    }
}
