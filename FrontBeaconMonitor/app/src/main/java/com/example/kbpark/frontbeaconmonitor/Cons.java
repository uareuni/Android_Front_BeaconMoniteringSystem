package com.example.kbpark.frontbeaconmonitor;

/**
 * Created by KBPark on 2017. 1. 31..
 */

public class Cons
{
    //public static final String BASE_URL = "http://10.0.2.2:4903"; // 에뮬레이터에서 localhost에 접근하기 위해서는 10.0.2.2 주소로 접근해야함!!!!!
    public static final String BASE_URL = "http://172.17.204.54:4902";

    // 1. login
    public static final String LOGIN_ADDITIONAL_URL = "/signin";
    public static final String LOGIN_SUCCESS = "200";
    public static final String LOGIN_FAILURE = "401";

    // 2. register
    public static final String REGISTER_ADDITIONAL_URL = "/register";
    public static final String REGISTER_SUCCESS = "200";
    public static final String REGISTER_FAILURE = "401";

    // 3. order
    public static final String ORDER_ADDITIONAL_URL = "/order/enqueue";
    public static final String ORDER_SUCCESS = "200";
    public static final String ORDER_FAILURE = "401";

    public static final String QUERY_ERROR = "500";

    public static final int MIN_PASSWORD = 4;

    public static final String FIRST_TITLE_NAME = "메뉴";
    public static final String SECOND_TITLE_NAME = "진행중인 이벤트";



    /** Beacon Cons **/

    //This is a default proximity uuid of the RECO
    public static final String RECO_UUID = "24DDF411-8CF1-440C-87CD-E368DAF9C93E";

    /**
     * SCAN_RECO_ONLY:
     *
     * true일 경우 레코 비콘만 스캔하며, false일 경우 모든 비콘을 스캔합니다.
     * RECOBeaconManager 객체 생성 시 사용합니다.
     */
    public static final boolean SCAN_RECO_ONLY = true;

    /**
     * ENABLE_BACKGROUND_RANGING_TIMEOUT:
     *
     * 백그라운드 ranging timeout을 설정합니다.
     * true일 경우, 백그라운드에서 입장한 region에서 ranging이 실행 되었을 때, 10초 후 자동으로 정지합니다.
     * false일 경우, 계속 ranging을 실행합니다. (배터리 소모율에 영향을 끼칩니다.)
     * RECOBeaconManager 객체 생성 시 사용합니다.
     */
    public static final boolean ENABLE_BACKGROUND_RANGING_TIMEOUT = true;

    /**
     * DISCONTINUOUS_SCAN:
     *
     * 일부 안드로이드 기기에서 BLE 장치들을 스캔할 때, 한 번만 스캔 후 스캔하지 않는 버그(참고: http://code.google.com/p/android/issues/detail?id=65863)가 있습니다.
     * 해당 버그를 SDK에서 해결하기 위해, RECOBeaconManager에 setDiscontinuousScan() 메소드를 이용할 수 있습니다.
     * 해당 메소드는 기기에서 BLE 장치들을 스캔할 때(즉, ranging 시에), 연속적으로 계속 스캔할 것인지, 불연속적으로 스캔할 것인지 설정하는 것입니다.
     * 기본 값은 FALSE로 설정되어 있으며, 특정 장치에 대해 TRUE로 설정하시길 권장합니다.
     */
    public static final boolean DISCONTINUOUS_SCAN = false;

    public static final int REQUEST_ENABLE_BT = 1;
    public static final int REQUEST_LOCATION = 10;



}


