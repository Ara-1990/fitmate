package com.the.fitmate.di

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.FirebaseDatabase
import com.the.fitmate.data.AppDb
import com.the.fitmate.data.ExerciseDao
import com.the.fitmate.data.ExerciseRepositoryImpl

import com.the.fitmate.data.UserRepositoryImpl
import com.the.fitmate.domain.BicepsBrestUseCase
import com.the.fitmate.domain.ExerciseRepository
import com.the.fitmate.domain.GetAllExerUseCase
import com.the.fitmate.domain.GetSavedExercisesUseCase
import com.the.fitmate.domain.InsertExerUseCase
import com.the.fitmate.domain.LoginUseCase
import com.the.fitmate.domain.RegisterUseCase
import com.the.fitmate.domain.SaveGetExerRepos
import com.the.fitmate.domain.ShoulderLegsUsaCase
import com.the.fitmate.domain.TricBackUseCase
import com.the.fitmate.domain.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDb(@ApplicationContext ctx: Context): AppDb =
        AppDb.getDatabase(ctx)

    @Provides
    fun provideExerciseDao(db: AppDb) = db.exerciseDao()


    @Provides @Singleton
    fun provideExerciseRepo(dao: ExerciseDao): ExerciseRepository =
        ExerciseRepositoryImpl(dao)


    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideUserRepository(database: FirebaseDatabase): UserRepository {
        return UserRepositoryImpl(database)
    }


    @Provides
    fun provideLoginUseCase(repo: UserRepository) =
        LoginUseCase(repo)

    @Provides
    fun provideRegisterUseCase(repo: UserRepository) =
        RegisterUseCase(repo)


    @Provides fun provideGetAllUseCase(repo: ExerciseRepository) = GetAllExerUseCase(repo)
    @Provides fun provideBicepsBresUseCase(repo: ExerciseRepository) = BicepsBrestUseCase(repo)
    @Provides fun provideTricBackUseCase(repo: ExerciseRepository) = TricBackUseCase(repo)
    @Provides fun provideShoulderLegsUseCase(repo: ExerciseRepository) = ShoulderLegsUsaCase(repo)


}


