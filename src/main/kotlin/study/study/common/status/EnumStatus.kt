package study.study.common.status

enum class Gender(val desc: String) {
    MALE("남"),
    WOMAN("여"),
}

// 추가
enum class ResultCode(val msg: String) {
    SUCCESS("정상 처리 되었습니다."),
    ERROR("에러가 발생했습니다.")
}

enum class Dormitory(val desc: String) {
    GU_A("고운A"),
    GU_B("고운B"),
    GU_C("고운C"),
    GS_11("경상11"),
    GS_12("경상12"),
    GS_13("경상13"),
    GS_14("경상14"),
}

enum class ROLE {
    MEMBER
}