package com.example.fastcampus

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/*
Retrofit 으로 요청을 하는데, 요청을 할 때마다 받아오는 객체가 다르다. (요청을 한번만 하진 않는다.)
어떤 객체가 올건지 적어줌
 */

class PostFromServer(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
) {
    // 부생성자 생성
    constructor(title: String, body: String): this(0, 0, title, body)
}

/*
url 하나에 대해서 여러 요청을 보낼 수 있다. -> GET, POST
POST 의 경우에는 Body에 raw 형태로 학생 정보를 서버에 넘겨줘야한다.
HashMap<key,value> -> Hash 는 사람이 읽을 수 없는 형태.
맨끝에 슬래시를 붙어야하는 경우도 있고, 안붙여도 되는 경우가 있다. -> 서버 개발자가 만드는 것
 */
interface RetrofitService {
    // 요청을 보냄 -> Retrofit 은 Call 로 한번 감싸야 한다. (문법)
    @GET("posts")
    fun getPostList(): Call<ArrayList<PostFromServer>>

    // 근본
    @POST("posts")
    fun registerPost(
        @Body params: HashMap<String, Any>
    ): Call<PostFromServer>

    // 개발자가 이해하기 좀 더 편한 코드 (컴퓨터는 결국 PostFromServer 객체가 아닌 HashMap 으로 알아들음)
    @POST("posts")
    fun easyRegisterPost(
        @Body post: PostFromServer
    ): Call<PostFromServer>
}