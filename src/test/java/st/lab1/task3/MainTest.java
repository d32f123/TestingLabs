package st.lab1.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/*Еще мгновение люди сидели молча, а потом человек, громко смеявшийся, засмеялся снова.
Девушка, которую он затащил с собой в бар, за последний час всем сердцем возненавидела его,
и, возможно, была бы рада узнать, что через полторы минуты он испарится, превратившись в облачко водорода, озона и оксида углерода.
Однако когда момент наступил, она была слишком занята собственным испарением, чтобы обратить на это внимание.
 */
class MainTest {

    private LaughingMan laughingMan;
    private Girl girl;
    private Time time;
    private Bar bar;


    static IntStream beforeBarParams() {
        return IntStream.range(1, 5);
    }

    static IntStream inBarParams() {
        return IntStream.range(1, 11);
    }

    static IntStream inBarForAnHourParams() {
        return IntStream.range(1, 12);
    }

    static IntStream inBarForOverAnHourParams() {
        return IntStream.range(1, 12);
    }

    private enum BarState {BEFORE_DRAGGED, JUST_DRAGGED, HOUR, HOUR_AND_MOMENT, HOUR_AND_MINUTE_AND_A_HALF}

    @BeforeEach
    void setUp() {
        laughingMan = new LaughingMan();
        girl = new Girl();
        bar = new Bar();
        time = new Time(bar);
    }

    private void barStateSetUp(BarState barState) {
        if (barState == BarState.BEFORE_DRAGGED)
            return;

        laughingMan.dragGirlIntoTheBar(girl, bar);

        if (barState == BarState.JUST_DRAGGED)
            return;

        time.passHour();

        if (barState == BarState.HOUR)
            return;

        time.passInstance();

        if (barState == BarState.HOUR_AND_MOMENT)
            return;

        time.passMinuteAndAHalf();
    }

    private void assertBeforeDraggedState(int i) {
        switch (i) {
            // Посмотрим, есть ли посетители в баре
            case 1:
                assertTrue(bar.getVisitors() != null, "No visitors in bar");
                break;
            case 2:
                assertTrue(bar.getVisitors().isSitting(), "Visitors are not sitting");
                break;
            // тихо ли в баре
            case 3:
                assertTrue(bar.isQuiet(), "Visitors are not silently sitting initially");
                break;
            // проверим, нет ли еще девушки с мужчиной в баре
            case 4:
                assertTrue(bar.getGirl() == null, "Girl is already in the bar");
                break;
            case 5:
                assertTrue(bar.getLaughingMan() == null,
                        "Man is already in bar");
                break;
        }
    }

    private void assertInBarState(int i) {
        switch (i) {
            case 1:
                // 1. проверим, есть ли девушка в баре теперь
                assertEquals(girl, bar.getGirl(), "The girl in the bar is not the one who was initially dragged");
                break;
            // 2. проверим, не рада ли девушка случайно по непонятной причине
            case 2:
                assertFalse(girl.isGlad(), "Girl is glad for some reason");
                break;
            // 3. проверим, не испарилась ли девушка случайно
            case 3:
                assertFalse(girl.isVaporized(), "Girl vaporized before she should've (whoops)");
                break;
            // 4. проверим, не возненавидела ли девушка мужчину (еще не должна)
            case 4:
                assertNull(girl.getManWhoWasHated());
                break;
            // 5. проверим, есть ли мужчина в баре
            case 5:
                assertEquals(laughingMan, bar.getLaughingMan(), "Man in the bar is not the man who dragged the girl");
                break;
            // 6. проверим, не смеялся ли мужчина еще
            case 6:
                assertEquals(0, laughingMan.getLaughCount(), "Man laughed at least once");
                break;
            // 7. проверим, не испралися ли мужчина случайно
            case 7:
                assertFalse(laughingMan.isVaporized(), "Man vaporized before he should've (whoops)");
                break;
            // 8. проверим целостность поля "девушки"
            case 8:
                assertEquals(girl, laughingMan.getGirlWhoWasDragged(), "LM's girl is not the same as the one he initially dragged");
                break;
            // 9. проверим, есть ли другие люди в баре
            case 9:
                assertTrue(bar.getVisitors() != null, "Visitors suddenly disappeared after the LM dragged the girl");
                break;
            // 10. сидят ли они
            case 10:
                assertTrue(bar.getVisitors().isSitting(), "Visitors are standing");
                break;
            // 11. тихо ли в баре
            case 11:
                assertTrue(bar.isQuiet(), "Bar is not quiet");
                break;
        }
    }

    private void assertInBarForAnHourState(int i) {
        switch (i) {
            //1) девушка есть
            case 1:
                assertEquals(girl, bar.getGirl(), "The girl in the bar is not the one who was initially dragged");
                break;
            //2) девушка НЕ рада
            case 2:
                assertFalse(girl.isGlad(), "Girl is glad for some reason");
                break;
            //3) девушка НЕ испарилась
            case 3:
                assertFalse(girl.isVaporized(), "Girl vaporized for some reason");
                break;
            //4) девушка ненавидит парня
            case 4:
                assertEquals(laughingMan, girl.getManWhoWasHated(), "Girl hated the wrong man");
                break;
            //
            //5) парень есть
            case 5:
                assertEquals(laughingMan, bar.getLaughingMan(), "LM missing from the bar");
                break;
            //6) парень смеялся один раз
            case 6:
                assertEquals(1, laughingMan.getLaughCount(), "LM laughed wrong number of times");
                break;
            //7) парень смеялся ГРОМКО
            case 7:
                assertTrue(laughingMan.isLaughingLoud(), "LM laughed not loud");
                break;
            //8) парень НЕ испарился
            case 8:
                assertFalse(laughingMan.isVaporized(), "LM vaporized for some reason");
                break;
            //9) у парня не подменилось поле "девушка"
            case 9:
                assertEquals(girl, laughingMan.getGirlWhoWasDragged(), "Man's girl field was compromised");
                break;
            //
            //10) другие люди есть
            case 10:
                assertNotNull(bar.getVisitors(), "Visitors are gone");
                break;
            //11) другие люди сидят
            case 11:
                assertTrue(bar.getVisitors().isSitting(), "Visitors are standing");
                break;
            //
            //12) в баре тихо
            case 12:
                assertTrue(bar.isQuiet(), "Bar is not quiet");
                break;
        }
    }

    private void assertInBarForAnHourAndAnInstanceTest(int i) {
        switch (i) {
            case 1:
//1) девушка есть
                assertEquals(girl, bar.getGirl(), "The girl in the bar is not the one who was initially dragged");
                break;
            case 2:
//2) девушка НЕ рада
                assertFalse(girl.isGlad(), "Girl is glad for some reason");
                break;
            case 3:
//3) девушка НЕ испарилась
                assertFalse(girl.isVaporized(), "Girl vaporized for some reason");
                break;
            case 4:
//4) девушка ненавидит парня
                assertEquals(laughingMan, girl.getManWhoWasHated(), "Girl hated the wrong man");
                break;
            case 5:
//
//5) парень есть
                assertEquals(laughingMan, bar.getLaughingMan(), "LM missing from the bar");
                break;
            case 6:
//6) парень смеялся ДВА раза
                assertEquals(2, laughingMan.getLaughCount(), "LM laughed wrong number of times");
                break;
            case 7:
//7) парень смеялся НЕ громко
                assertFalse(laughingMan.isLaughingLoud(), "LM laughed loud");
                break;
            case 8:
//8) парень НЕ испарился
                assertFalse(laughingMan.isVaporized(), "LM vaporized for some reason");
                break;
            case 9:
//9) у парня не подменилось поле "девушка"
                assertEquals(girl, laughingMan.getGirlWhoWasDragged(), "Man's girl field was compromised");
                break;
            case 10:
//
//10) другие люди есть
                assertNotNull(bar.getVisitors(), "Visitors are gone");
                break;
            case 11:
//11) другие люди сидят
                assertTrue(bar.getVisitors().isSitting(), "Visitors are standing");
                break;
            case 12:
//
//12) в баре не тихо
                assertFalse(bar.isQuiet(), "Bar is not quiet");
                break;
        }
    }

    private void assertInBarForOverAnHourTest(int i) {

        switch (i) {
            case 1:
//1) девушка есть
                assertEquals(girl, bar.getGirl(), "The girl in the bar is not the one who was initially dragged");
                break;
            case 2:
//2) девушка НЕ рада
                assertFalse(girl.isGlad(), "Girl is glad for some reason");
                break;
            case 3:
//3) девушка ИСПАРЯЕТСЯ
                assertTrue(girl.isVaporized(), "Girl did not vaporize for some reason");
                break;
            case 4:
//4) девушка ненавидит парня
                assertEquals(laughingMan, girl.getManWhoWasHated(), "Girl hated the wrong man");

//
                break;
            case 5:
//5) парень есть
                assertEquals(laughingMan, bar.getLaughingMan(), "LM missing from the bar");
                break;
            case 6:
//6) парень смеялся ДВА раза
                assertEquals(2, laughingMan.getLaughCount(), "LM laughed wrong number of times");
                break;
            case 7:
//7) парень смеялся НЕ громко
                assertFalse(laughingMan.isLaughingLoud(), "LM laughed loud");
                break;
            case 8:
//8) парень ИСПАРЯЕТСЯ
                assertTrue(laughingMan.isVaporized(), "LM did not vaporize for some reason");
                break;
            case 9:
//9) у парня не подменилось поле "девушка"
                assertEquals(girl, laughingMan.getGirlWhoWasDragged(), "Man's girl field was compromised");
                break;
            case 10:
//
//10) другие люди есть
                assertNotNull(bar.getVisitors(), "Visitors are gone");
                break;
            case 11:
//11) другие люди сидят
                assertTrue(bar.getVisitors().isSitting(), "Visitors are standing");
                break;
            case 12:
//
//12) в баре не тихо
                assertFalse(bar.isQuiet(), "Bar is not quiet");
                break;
        }
    }

    @ParameterizedTest
    @MethodSource("beforeBarParams")
    void initialConditionFineTest(int i) {

        barStateSetUp(BarState.BEFORE_DRAGGED);

        assertBeforeDraggedState(i);
    }

    @ParameterizedTest
    @MethodSource("beforeBarParams")
    void beforeBarAddInstanceTest(int i) {

        barStateSetUp(BarState.BEFORE_DRAGGED);

        time.passInstance();

        assertBeforeDraggedState(i);

    }

    @Test
    void beforeBarAddMinuteThrowsTest() {

        barStateSetUp(BarState.BEFORE_DRAGGED);

        assertThrows(NullPointerException.class, () -> time.passMinuteAndAHalf());
    }

    @ParameterizedTest
    @MethodSource("beforeBarParams")
    void beforeBarAddMinuteResetStateTest(int i) {

        barStateSetUp(BarState.BEFORE_DRAGGED);

        try {
            time.passMinuteAndAHalf();
        } catch (NullPointerException e) {
        }

        assertBeforeDraggedState(i);

        // проверим, установлено ли время в 0
        assertEquals(0, time.getSeconds(), "Time did not reset its state (seconds)");
        assertEquals(0, time.getInstances(), "Time did not reset its state (instances)");

    }

    @Test
    void beforeBarAddHourThrowsTest() {

        barStateSetUp(BarState.BEFORE_DRAGGED);

        assertThrows(NullPointerException.class, () -> time.passHour());
    }

    @ParameterizedTest
    @MethodSource("beforeBarParams")
    void beforeBarAddHourResetStateTest(int i) {

        barStateSetUp(BarState.BEFORE_DRAGGED);

        try {
            time.passHour();
        } catch (NullPointerException e) {
        }

        assertBeforeDraggedState(i);

        // проверим, установлено ли время в 0
        assertEquals(0, time.getSeconds(), "Time did not reset its state (seconds)");
        assertEquals(0, time.getInstances(), "Time did not reset its state (instances)");
    }

    @Test
    void beforeBarAddHourUsingMinutesThrowsTest() {
        barStateSetUp(BarState.BEFORE_DRAGGED);

        assertThrows(NullPointerException.class, () -> {
            for (int i = 0; i < 40; ++i)
                time.passMinuteAndAHalf();
        });
    }

    @ParameterizedTest
    @MethodSource("beforeBarParams")
    void beforeBarAddHourUsingMinutesResetStateTest(int i) {
        barStateSetUp(BarState.BEFORE_DRAGGED);

        try {
            for (int j = 0; j < 40; ++j)
                time.passMinuteAndAHalf();
        } catch (NullPointerException e) {
        }

        assertBeforeDraggedState(i);

        // проверим, установлено ли время в 0
        assertEquals(0, time.getSeconds(), "Time did not reset its state (seconds)");
        assertEquals(0, time.getInstances(), "Time did not reset its state (instances)");
    }

    @ParameterizedTest
    @MethodSource("inBarParams")
    void beforeBarDragIntoTheBarPositiveTest(int i) {

        barStateSetUp(BarState.JUST_DRAGGED);

        assertInBarState(i);
    }

    @Test
    void inBarDragIntoTheBarThrowExceptionTest() {
        barStateSetUp(BarState.JUST_DRAGGED);

        assertThrows(IllegalArgumentException.class, () -> laughingMan.dragGirlIntoTheBar(girl, bar));
    }

    @ParameterizedTest
    @MethodSource("inBarParams")
    void inBarDragIntoTheBarResetStateTest(int i) {
        barStateSetUp(BarState.JUST_DRAGGED);

        try {
            laughingMan.dragGirlIntoTheBar(girl, bar);
        } catch (IllegalArgumentException e) {
        }

        assertInBarState(i);
    }

    @ParameterizedTest
    @MethodSource("inBarParams")
    void inBarAddInstanceDoNothingTest(int i) {
        barStateSetUp(BarState.JUST_DRAGGED);

        time.passInstance();

        assertInBarState(i);
    }

    @ParameterizedTest
    @MethodSource("inBarParams")
    void inBarAddMinuteDoNothingTest(int i) {
        barStateSetUp(BarState.JUST_DRAGGED);

        time.passMinuteAndAHalf();

        assertInBarState(i);
    }

    @ParameterizedTest
    @MethodSource("inBarForAnHourParams")
    void inBarAddMinuteManyTimesPositiveTest(int i) {
        barStateSetUp(BarState.JUST_DRAGGED);

        for (int j = 0; j < 40; ++j)
            time.passMinuteAndAHalf();

        assertInBarForAnHourState(i);
    }

    @ParameterizedTest
    @MethodSource("inBarForAnHourParams")
    void inBarAddHourPositiveTest(int i) {
        barStateSetUp(BarState.JUST_DRAGGED);

        time.passHour();

        assertInBarForAnHourState(i);
    }

    @Test
    void inBarForAnHourDragIntoTheBarThrowsTest() {
        barStateSetUp(BarState.HOUR);

        assertThrows(IllegalArgumentException.class, () -> laughingMan.dragGirlIntoTheBar(girl, bar));
    }

    @ParameterizedTest
    @MethodSource("inBarForAnHourParams")
    void inBarForAnHourDragIntoTheBarResetStateTest(int i) {
        barStateSetUp(BarState.HOUR);

        try {
            laughingMan.dragGirlIntoTheBar(girl, bar);
        } catch (IllegalArgumentException e) {
        }

        assertInBarForAnHourState(i);
    }

    @ParameterizedTest
    @MethodSource("inBarForOverAnHourParams")
    void inBarForAnHourPassInstanceToHourAndInstanceStateTest(int i) {
        barStateSetUp(BarState.HOUR);

        time.passInstance();

        assertInBarForAnHourAndAnInstanceTest(i);
    }

    @ParameterizedTest
    @MethodSource("inBarForOverAnHourParams")
    void inBarForAnHourPassMinuteToHourAndMinuteStateTest(int i) {
        barStateSetUp(BarState.HOUR);

        time.passMinuteAndAHalf();

        assertInBarForOverAnHourTest(i);
    }

    @ParameterizedTest
    @MethodSource("inBarForOverAnHourParams")
    void inBarForAnHourPassHourToHourAndMinuteStateTest(int i) {
        barStateSetUp(BarState.HOUR);

        time.passHour();

        assertInBarForOverAnHourTest(i);
    }

    @Test
    void inBarForAnHourInstanceDragIntoTheBarThrowsTest() {
        barStateSetUp(BarState.HOUR_AND_MOMENT);

        assertThrows(IllegalArgumentException.class, () -> laughingMan.dragGirlIntoTheBar(girl, bar));
    }

    @ParameterizedTest
    @MethodSource("inBarForOverAnHourParams")
    void inBarForAnHourInstanceDragIntoTheBarResetStateTest(int i) {
        barStateSetUp(BarState.HOUR_AND_MOMENT);

        try {
            laughingMan.dragGirlIntoTheBar(girl, bar);
        } catch (IllegalArgumentException ex) {
        }

        assertInBarForAnHourAndAnInstanceTest(i);
    }

    @ParameterizedTest
    @MethodSource("inBarForOverAnHourParams")
    void inBarForAnHourInstancePassInstanceDoNothingTest(int i) {
        barStateSetUp(BarState.HOUR_AND_MOMENT);

        time.passInstance();

        assertInBarForAnHourAndAnInstanceTest(i);
    }

    @ParameterizedTest
    @MethodSource("inBarForOverAnHourParams")
    void inBarForAnHourInstancePassMinuteToHourAndMinuteStateTest(int i) {
        barStateSetUp(BarState.HOUR_AND_MOMENT);

        time.passMinuteAndAHalf();

        assertInBarForOverAnHourTest(i);
    }

    @ParameterizedTest
    @MethodSource("inBarForOverAnHourParams")
    void inBarForAnHourInstancePassHourToHourAndMinuteStateTest(int i) {
        barStateSetUp(BarState.HOUR_AND_MOMENT);

        time.passHour();

        assertInBarForOverAnHourTest(i);
    }

    @Test
    void inBarForHourAndMinuteDragThrowsTest() {
        barStateSetUp(BarState.HOUR_AND_MINUTE_AND_A_HALF);

        assertThrows(IllegalArgumentException.class, () -> laughingMan.dragGirlIntoTheBar(girl, bar));
    }

    @ParameterizedTest
    @MethodSource("inBarForOverAnHourParams")
    void inBarForHourAndMinuteDragResetStateTest(int i) {
        barStateSetUp(BarState.HOUR_AND_MINUTE_AND_A_HALF);

        try {
            laughingMan.dragGirlIntoTheBar(girl, bar);
        } catch (IllegalArgumentException ex) {
        }

        assertInBarForOverAnHourTest(i);
    }

    @ParameterizedTest
    @MethodSource("inBarForOverAnHourParams")
    void inBarForHourAndMinutePassInstanceDoNothingTest(int i) {
        barStateSetUp(BarState.HOUR_AND_MINUTE_AND_A_HALF);

        time.passInstance();

        assertInBarForOverAnHourTest(i);
    }

    @ParameterizedTest
    @MethodSource("inBarForOverAnHourParams")
    void inBarForHourAndMinutePassMinuteDoNothingTest(int i) {
        barStateSetUp(BarState.HOUR_AND_MINUTE_AND_A_HALF);

        time.passMinuteAndAHalf();

        assertInBarForOverAnHourTest(i);
    }

    @ParameterizedTest
    @MethodSource("inBarForOverAnHourParams")
    void inBarForHourAndMinutePassHourDoNothingTest(int i) {
        barStateSetUp(BarState.HOUR_AND_MINUTE_AND_A_HALF);

        time.passHour();

        assertInBarForOverAnHourTest(i);
    }

    @Test
    void mainPositiveTest() {

        // Еще мгновение люди сидели молча,
        // Посмотрим, есть ли посетители в баре
        assertTrue(bar.getVisitors() != null, "No visitors in bar");
        assertTrue(bar.getVisitors().isSitting(), "Visitors are not sitting initially");
        assertTrue(bar.isQuiet(), "Bar is not quiet");
        // проверим, нет ли еще девушки с мужчиной в баре
        assertTrue(bar.getGirl() == null && bar.getLaughingMan() == null,
                "Girl and man are already in bar");

        // Девушка, которую он затащил с собой в бар,
        // затащим девушку в бар
        laughingMan.dragGirlIntoTheBar(girl, bar);

        // проверим, есть ли девушка в баре теперь
        assertEquals(girl, laughingMan.getGirlWhoWasDragged(), "LM's girl is not the same as the one he initially dragged");
        assertEquals(girl, bar.getGirl(), "The girl in the bar is not the one who was initially dragged");
        // проверим, есть ли мужчина в баре
        assertEquals(laughingMan, bar.getLaughingMan(), "Man in the bar is not the man who dragged the girl");
        // проверим, есть ли другие люди в баре
        assertTrue(bar.getVisitors() != null, "Visitors suddenly disappeared after the LM dragged the girl");
        assertTrue(bar.getVisitors().isSitting(), "Visitors are not sitting after LM dragged the girl");
        assertTrue(bar.isQuiet());

        // Еще мгновение люди сидели молча, а потом человек, громко смеявшийся, засмеялся снова.
        // за последний час всем сердцем возненавидела его,
        // Получается, что в течение их времяпровождения в баре, человек громко смеялся
        time.passHour();
        // проверим, смеялся ли человек громко и смеялся ли он один раз
        assertTrue(laughingMan.isLaughingLoud(), "Man did not laugh loud during the first hour");
        assertEquals(1, laughingMan.getLaughCount(), "Man laughed not 1 time during the first hour");
        // проверим, до сих пор ли люди сидят тихо
        assertTrue(bar.getVisitors().isSitting(), "Visitors are not silently sitting after an hour");

        // проверим, возненавидела ли девушка его
        assertEquals(laughingMan, girl.getManWhoWasHated(), "Girl did not hate the laughing man after one hour");


        // теперь момент проходит, и человек смеется снова
        time.passInstance();
        // проверяем, смеялся ли человек снова и смеялся ли он НЕ громко
        assertTrue(laughingMan.isLaughing() && !laughingMan.isLaughingLoud(),
                "Lauging man was not laughing or was laughing loud after the first moment");

        // и, возможно, была бы рада узнать, что через полторы минуты он испарится, превратившись в облачко водорода, озона и оксида углерода.
        // Однако когда момент наступил, она была слишком занята собственным испарением, чтобы обратить на это внимание.

        // тут, через полторы минуты, они оба начинают испаряться. Девушка должна бы была радоваться испарению мужчины,
        // но так как и она испаряется, то ей не до него
        time.passMinuteAndAHalf();
        // проверим, испаряются ли мужчина и девушка
        assertTrue(laughingMan.isVaporized(), "Laughing man is not vaporized");
        assertTrue(girl.isVaporized(), "Girl is not vaporized");
        // проверим, рада ли девушка (она не должна быть рада)
        assertFalse(girl.isGlad(), "Girl is glad for some reason");
    }
}