import com.android.build.api.dsl.Packaging
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id ("dagger.hilt.android.plugin")
    id ("com.google.devtools.ksp") version "1.6.10-1.0.2"
    id("com.google.gms.google-services")

}

android {
    namespace = "br.senai.sp.jandira.limpeanapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.senai.sp.jandira.limpeanapp"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    buildToolsVersion = "33.0.1"
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:compose")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.datastore:datastore-core:1.0.0")
    implementation("androidx.datastore:datastore-preferences-core:1.0.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("com.google.firebase:firebase-common-ktx:20.4.2")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    //COMPOSE UI DEPENDENCIES:

    // - Navigation Compose para navegação da aplicação como rotas.
    implementation("androidx.navigation:navigation-compose:2.7.4")

    // - Coil para renderizar imagens junto com o Compose
    implementation("io.coil-kt:coil-compose:2.3.0")

    //- Ícones do Compose para utilizar diretamente dele
    implementation("androidx.compose.material:material-icons-extended:compose_ui_version")

    // - Form Builder para fazer formulários mais rápidos
    implementation("com.github.jkuatdsc:form-builder:1.0.7")

    // VIEW MODEL DEPENDENCIES:

    // Dependências do View Model também com o Compose para gerenciar de estado da UI
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")


    // API DEPENDENCIES

    // Retrofit como Client HTTP - Conversor de GSON
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // OkHttp3 Loggin Inteceptor como interceptor de requisições
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.1")
    // Conversor de JSON da Google : JSON
    implementation("com.google.code.gson:gson:2.9.0")

    // COROUTINES DEPENDENCIES - Gerenciar estados assíncronos
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")


    // TESTS DEPENDENCIES

    // Para testes unitários:
    testImplementation("androidx.test:core:1.5.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.1")
    testImplementation("io.mockk:mockk:1.10.5")




    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.0-alpha07")


    // For instrumentation tests
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48.1")
    androidTestAnnotationProcessor("com.google.dagger:hilt-compiler:2.48.1")

    // For local unit tests
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("com.google.dagger:hilt-android-testing:2.48.1")
    testAnnotationProcessor("com.google.dagger:hilt-compiler:2.48.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")


    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.44")


    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")


    //Google Assertions
    testImplementation("com.google.truth:truth:1.1.3")
    androidTestImplementation("com.google.truth:truth:1.1.3")

    // Compose dependencies
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    //Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.48.1")
    kapt ("com.google.dagger:hilt-android-compiler:2.44")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")

    //Datastore - Preferences
    implementation ("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore-preferences-core:1.0.0")


//    //Splash Screen
    implementation ("androidx.core:core-splashscreen:1.0.0")


    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0")

 //Google maps APi
    implementation ("com.google.maps.android:maps-compose:4.3.0")
    implementation ("com.google.android.gms:play-services-maps:18.2.0")

    //Firebase Dependencies
    implementation("com.google.firebase:firebase-storage-ktx:20.3.0")
    implementation("com.google.firebase:firebase-firestore:24.9.1")
    implementation("com.google.firebase:firebase-storage:20.3.0")



}
 kapt {
    correctErrorTypes = true
}
//