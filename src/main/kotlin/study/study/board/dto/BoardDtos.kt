package study.study.board.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

// 게시글 작성 시 받을 정보
data class BoardDtoRequest(

    val id: Long?,

    @field:NotBlank
    @JsonProperty("title")
    private val _title: String?,

    @field:NotBlank
    @JsonProperty("description")
    private val _description: String?,

    @field:NotBlank
    @JsonProperty("loginId")
    private val _loginId: String?,
    
//    생성일자는 Entity에서 자동으로 생성되므로 Request에서는 필요하지 않음.

//    @field:NotBlank
//    @JsonProperty("creationDate")
//    private val _creationDate: String?,
    
) {
    val title: String
        get() = _title!!
    val description: String
        get() = _description!!

}

data class DeleteDto(
    // 삭제 하려면 Post의 Id, 현재 loginid 필요.

    @field:NotNull
    @JsonProperty("postId")
    val postId: Long
)