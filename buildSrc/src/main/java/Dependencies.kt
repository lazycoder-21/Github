object Dependencies {

    const val CUSTOM_VIEWS = "com.github.lostankit7:AndroidCustomViews:1.2"

    //lifecycle
    const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    const val LIVE_DATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
    const val LIFE_CYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"

    //google material
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"

    //androidX
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:1.5.1"
    const val SUPPORT = "androidx.legacy:legacy-support-v4:1.0.0"

    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"


    //coroutines
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"


    //dagger
    const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER}"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"


    //retrofit
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val GSON_CONVERTOR = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"

    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"


    //room
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"

    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"

    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"

    //hilt
    const val HILT = "com.google.dagger:hilt-android:2.38.1"
    const val KAPT_HILT_COMPILER = "com.google.dagger:hilt-android-compiler:2.37"

    private const val PAGING_VERSION = "3.0.0"
    const val PAGING_RUNTIME = "androidx.paging:paging-runtime:$PAGING_VERSION"
}