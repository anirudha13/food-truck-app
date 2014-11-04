package my.anirudha.foodtruckmap.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: anirudha
 * Date: 04/11/14
 * Time: 4:01 PM
 *
 * @author anirudha
 */
public enum ApplicationStatusEnum {
    UNKNOWN,
    REQUESTED,
    APPROVED,
    PENDING,
    EXPIRED,
    SUSPEND,
    ONHOLD,
    POSTAPPROVED;

    private static final Map<String, ApplicationStatusEnum> NAME_MAP = new HashMap<String, ApplicationStatusEnum>();

    static {
        for (ApplicationStatusEnum applicationStatusEnum : ApplicationStatusEnum.values()) {
            String enumName = applicationStatusEnum.name();
            NAME_MAP.put(enumName, applicationStatusEnum); //in uppercase
            NAME_MAP.put(enumName.toLowerCase(), applicationStatusEnum); //in lowercase
        }
    }

    public static ApplicationStatusEnum fromString(String str) {
        ApplicationStatusEnum returnEnum = UNKNOWN;
        if (NAME_MAP.containsKey(str.toLowerCase())) {
            returnEnum = NAME_MAP.get(str.toLowerCase());
        }
        return returnEnum;
    }

}
