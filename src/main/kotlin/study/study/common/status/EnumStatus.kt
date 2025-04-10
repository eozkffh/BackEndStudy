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