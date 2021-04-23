package com.skillbox.github.data

import net.openid.appauth.ResponseTypeValues

object AuthConfig {

    const val AUTH_URI = "https://github.com/login/oauth/authorize"
    const val TOKEN_URI = "https://github.com/login/oauth/access_token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "user,repo"

    const val CLIENT_ID = "6874b9433d7254be56ae"
    const val CLIENT_SECRET = "3b80034304673a589f08c1313a6d092564572a46"
    const val CALLBACK_URL = "skillbox://skillbox.ru/callback"
}
