package com.example.musicbrainzexplorer.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder().apply {
                addHeader("User-Agent", "MusicBrainzExplorer/0.0.0 (al@dhsound.co.uk)")
                addHeader("Accept", "application/json")
            }.build()
            chain.proceed(newRequest)
        }
        .addInterceptor(MockInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    private const val BASE_URL = "https://musicbrainz.org/ws/2/"
}

class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val responseBody = if (request.url().toString().contains("release-groups")) {
            DETAIL_RESPONSE
        } else {
            SEARCH_RESPONSE
        }
        return Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("OK")
            .body(ResponseBody.create(MediaType.parse("application/json"), responseBody))
            .code(200)
            .build()
    }

    companion object {
        private const val SEARCH_RESPONSE = """
{
  "created": "2021-08-09T20:31:40.093Z",
  "count": 153,
  "offset": 0,
  "artists": [
    {
      "id": "b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 100,
      "name": "The Beatles",
      "sort-name": "Beatles, The",
      "country": "GB",
      "area": {
        "id": "8a754a16-0027-3a29-b6d7-2b40ea0481ed",
        "type": "Country",
        "type-id": "06dd0ae4-8c74-30bb-b43d-95dcedf961de",
        "name": "United Kingdom",
        "sort-name": "United Kingdom",
        "life-span": {
          "ended": null
        }
      },
      "begin-area": {
        "id": "c249c30e-88ab-4b2f-a745-96a25bd7afee",
        "type": "City",
        "type-id": "6fd8f29a-3d0a-32fc-980d-ea697b69da78",
        "name": "Liverpool",
        "sort-name": "Liverpool",
        "life-span": {
          "ended": null
        }
      },
      "isnis": [
        "0000000121707484"
      ],
      "life-span": {
        "begin": "1957-03",
        "end": "1970-04-10",
        "ended": true
      },
      "aliases": [
        {
          "sort-name": "披头士",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "披头士",
          "locale": "zh_Hans",
          "type": "Artist name",
          "primary": true,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "披頭四",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "披頭四",
          "locale": "zh_Hant",
          "type": "Artist name",
          "primary": true,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "Silver Beatles, The",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "The Silver Beatles",
          "locale": "en",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "Beatles, I",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "I Beatles",
          "locale": "it",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "Beatles, Die",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "Die Beatles",
          "locale": "de",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "ბითლზი",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "ბითლზი",
          "locale": "ka",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "بیٹلز",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "بیٹلز",
          "locale": "ur",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "เดอะบีเทิลส์",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "เดอะบีเทิลส์",
          "locale": "th",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "ద బీటిల్స్",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "ద బీటిల్స్",
          "locale": "te",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "பீட்டில்ஸ்",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "பீட்டில்ஸ்",
          "locale": "ta",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "ਦ ਬੀਟਲਜ਼",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "ਦ ਬੀਟਲਜ਼",
          "locale": "pa",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "Битлси",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "Битлси",
          "locale": "mk",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "ദി ബീറ്റിൽസ്",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "ദി ബീറ്റിൽസ്",
          "locale": "ml",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "ದಿ ಬೀಟಲ್ಸ್",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "ದಿ ಬೀಟಲ್ಸ್",
          "locale": "kn",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "Bítlarnir",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "Bítlarnir",
          "locale": "is",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "द बीटल्स",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "द बीटल्स",
          "locale": "hi",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "더 비틀즈",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "더 비틀즈",
          "locale": "ko",
          "type": "Artist name",
          "primary": true,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "הביטלס",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "הביטלס",
          "locale": "he",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "ધ બિટલ્સ",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "ધ બિટલ્સ",
          "locale": "gu",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "بیتلز",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "بیتلز",
          "locale": "fa",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "দ্য বিটল্‌স",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "দ্য বিটল্‌স",
          "locale": "bn",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "البيتلز",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "البيتلز",
          "locale": "ar",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "Beatles, Los",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "Los Beatles",
          "locale": "es",
          "type": "Artist name",
          "primary": true,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "ዘ ቢተልስ",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "ዘ ቢተልስ",
          "locale": "am",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "ビートルズ（ザ）",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "ザ・ビートルズ",
          "locale": "ja",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "Beatles, Les",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "Les Beatles",
          "locale": "fr",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "B",
          "type-id": "1937e404-b981-3cb7-8151-4c86ebfc8d8e",
          "name": "B",
          "locale": null,
          "type": "Search hint",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "Битлз",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "Битлз",
          "locale": "ru",
          "type": "Artist name",
          "primary": true,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "fab four",
          "type-id": "1937e404-b981-3cb7-8151-4c86ebfc8d8e",
          "name": "fab four",
          "locale": null,
          "type": "Search hint",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "Beatles, The",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "The Beatles",
          "locale": "en",
          "type": "Artist name",
          "primary": true,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "Beetles",
          "type-id": "1937e404-b981-3cb7-8151-4c86ebfc8d8e",
          "name": "Beetles",
          "locale": null,
          "type": "Search hint",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "Be",
          "type-id": "1937e404-b981-3cb7-8151-4c86ebfc8d8e",
          "name": "Be",
          "locale": null,
          "type": "Search hint",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "Beat",
          "type-id": "1937e404-b981-3cb7-8151-4c86ebfc8d8e",
          "name": "Beat",
          "locale": null,
          "type": "Search hint",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "Beatles",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "Beatles",
          "locale": "en",
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        },
        {
          "sort-name": "Savage Young Beatles, The",
          "name": "The Savage Young Beatles",
          "locale": null,
          "type": null,
          "primary": null,
          "begin-date": null,
          "end-date": null
        }
      ],
      "tags": [
        {
          "count": 23,
          "name": "rock"
        },
        {
          "count": 10,
          "name": "pop"
        },
        {
          "count": -1,
          "name": "progressive rock"
        },
        {
          "count": 0,
          "name": "80s"
        },
        {
          "count": -2,
          "name": "heavy metal"
        },
        {
          "count": -2,
          "name": "pop-rock"
        },
        {
          "count": -2,
          "name": "orchestral"
        },
        {
          "count": -1,
          "name": "experimental"
        },
        {
          "count": 13,
          "name": "british"
        },
        {
          "count": -1,
          "name": "uk"
        },
        {
          "count": 0,
          "name": "60s"
        },
        {
          "count": -1,
          "name": "hard rock"
        },
        {
          "count": -2,
          "name": "indie rock"
        },
        {
          "count": -1,
          "name": "folk rock"
        },
        {
          "count": 3,
          "name": "psychedelic rock"
        },
        {
          "count": -1,
          "name": "psychedelic"
        },
        {
          "count": -2,
          "name": "blues rock"
        },
        {
          "count": -2,
          "name": "britpop"
        },
        {
          "count": -1,
          "name": "art rock"
        },
        {
          "count": 0,
          "name": "folk-rock"
        },
        {
          "count": 0,
          "name": "baroque pop"
        },
        {
          "count": 0,
          "name": "classic rock"
        },
        {
          "count": 4,
          "name": "psychedelic pop"
        },
        {
          "count": 8,
          "name": "pop rock"
        },
        {
          "count": 3,
          "name": "british invasion"
        },
        {
          "count": 0,
          "name": "rock and roll"
        },
        {
          "count": -1,
          "name": "rock roll"
        },
        {
          "count": 0,
          "name": "english"
        },
        {
          "count": -1,
          "name": "sunshine pop"
        },
        {
          "count": -1,
          "name": "folk pop"
        },
        {
          "count": 0,
          "name": "liverpool"
        },
        {
          "count": -1,
          "name": "parlophone"
        },
        {
          "count": -1,
          "name": "united kingdom"
        },
        {
          "count": -1,
          "name": "hair metal"
        },
        {
          "count": 0,
          "name": "1"
        },
        {
          "count": 0,
          "name": "bbc"
        },
        {
          "count": 0,
          "name": "classic pop and rock"
        },
        {
          "count": -1,
          "name": "singer songwriter"
        },
        {
          "count": 0,
          "name": "classical pop"
        },
        {
          "count": 0,
          "name": "instrumental pop"
        },
        {
          "count": 2,
          "name": "merseybeat"
        },
        {
          "count": 0,
          "name": "adult alternative pop rock"
        },
        {
          "count": -1,
          "name": "tribute albums"
        },
        {
          "count": 0,
          "name": "british psychedelia"
        },
        {
          "count": 0,
          "name": "orchestral pop"
        },
        {
          "count": -1,
          "name": "pop-metal"
        },
        {
          "count": 0,
          "name": "male vocalists"
        },
        {
          "count": -1,
          "name": "the-beatles"
        },
        {
          "count": 0,
          "name": "boys band"
        },
        {
          "count": 0,
          "name": "abbey road"
        },
        {
          "count": 0,
          "name": "rock & roll"
        },
        {
          "count": -1,
          "name": "pop/rock"
        },
        {
          "count": -1,
          "name": "rhythm & blues"
        },
        {
          "count": 0,
          "name": "overrated"
        },
        {
          "count": -1,
          "name": "art pop"
        },
        {
          "count": 0,
          "name": "one beatles"
        },
        {
          "count": 0,
          "name": "a filk artist"
        },
        {
          "count": 0,
          "name": "british rhythm & blues"
        },
        {
          "count": -1,
          "name": "beat music"
        }
      ]
    },
    {
      "id": "5e685f9e-83bb-423c-acfa-487e34f15ffd",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 77,
      "name": "The Tape-beatles",
      "sort-name": "Tape-beatles, The",
      "country": "US",
      "area": {
        "id": "489ce91b-6658-3307-9877-795b68554c98",
        "type": "Country",
        "type-id": "06dd0ae4-8c74-30bb-b43d-95dcedf961de",
        "name": "United States",
        "sort-name": "United States",
        "life-span": {
          "ended": null
        }
      },
      "begin-area": {
        "id": "3fc3b364-665e-4875-a9ff-3699cd716152",
        "type": "City",
        "type-id": "6fd8f29a-3d0a-32fc-980d-ea697b69da78",
        "name": "Iowa City",
        "sort-name": "Iowa City",
        "life-span": {
          "ended": null
        }
      },
      "disambiguation": "US multimedia group",
      "life-span": {
        "begin": "1986-12",
        "ended": null
      },
      "aliases": [
        {
          "sort-name": "Tape-Beatles",
          "name": "Tape-Beatles",
          "locale": null,
          "type": null,
          "primary": null,
          "begin-date": null,
          "end-date": null
        }
      ]
    },
    {
      "id": "1019b551-eba7-4e7c-bc7d-eb427ef54df2",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 76,
      "name": "Blues Beatles",
      "sort-name": "Blues Beatles",
      "country": "BR",
      "area": {
        "id": "f45b47f8-5796-386e-b172-6c31b009a5d8",
        "type": "Country",
        "type-id": "06dd0ae4-8c74-30bb-b43d-95dcedf961de",
        "name": "Brazil",
        "sort-name": "Brazil",
        "life-span": {
          "ended": null
        }
      },
      "disambiguation": "blues rock, Brazil",
      "life-span": {
        "ended": null
      }
    },
    {
      "id": "5a45e8c5-e8e5-4f05-9429-6dd00f0ab50b",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 75,
      "name": "Instrumental Beatles",
      "sort-name": "Instrumental Beatles",
      "life-span": {
        "ended": null
      }
    },
    {
      "id": "e897e5fc-2707-49c8-8605-be82b4664dc5",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 75,
      "name": "Sex Beatles",
      "sort-name": "Sex Beatles",
      "disambiguation": "Brazilian rock band",
      "life-span": {
        "ended": null
      }
    },
    {
      "id": "74e70126-def2-4b76-a001-ed3b96080e24",
      "score": 74,
      "name": "Powdered Beatles",
      "sort-name": "Powdered Beatles",
      "disambiguation": "Richard Ramirez",
      "life-span": {
        "ended": null
      }
    },
    {
      "id": "bc569a61-dd62-4758-86c6-e99dcb1fdda6",
      "score": 74,
      "name": "Tokyo Beatles",
      "sort-name": "Tokyo Beatles",
      "country": "JP",
      "area": {
        "id": "2db42837-c832-3c27-b4a3-08198f75693c",
        "type": "Country",
        "type-id": "06dd0ae4-8c74-30bb-b43d-95dcedf961de",
        "name": "Japan",
        "sort-name": "Japan",
        "life-span": {
          "ended": null
        }
      },
      "begin-area": {
        "id": "8dc97297-ac95-4d33-82bc-e07fab26fb5f",
        "type": "Subdivision",
        "type-id": "fd3d44c5-80a1-3842-9745-2c4972d35afa",
        "name": "Tokyo",
        "sort-name": "Tokyo",
        "life-span": {
          "ended": null
        }
      },
      "disambiguation": "Japanese",
      "life-span": {
        "ended": null
      }
    },
    {
      "id": "3133aeb8-9982-4e11-a8ff-5477996a80bf",
      "score": 74,
      "name": "Beatles Chillout",
      "sort-name": "Beatles Chillout",
      "life-span": {
        "ended": null
      }
    },
    {
      "id": "ad60d963-44f1-4b41-b785-8284edcaaffe",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 74,
      "name": "Counterfeit Beatles",
      "sort-name": "Counterfeit Beatles",
      "country": "GB",
      "area": {
        "id": "8a754a16-0027-3a29-b6d7-2b40ea0481ed",
        "type": "Country",
        "type-id": "06dd0ae4-8c74-30bb-b43d-95dcedf961de",
        "name": "United Kingdom",
        "sort-name": "United Kingdom",
        "life-span": {
          "ended": null
        }
      },
      "disambiguation": "The Beatles tribute band",
      "life-span": {
        "ended": null
      }
    },
    {
      "id": "de0769fa-7c32-4706-9c8c-03631c90f208",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 74,
      "name": "Shitty Beatles",
      "sort-name": "Shitty Beatles",
      "area": {
        "id": "dbe53871-db21-47ea-a0c3-3ab58317a3ab",
        "type": "City",
        "type-id": "6fd8f29a-3d0a-32fc-980d-ea697b69da78",
        "name": "Gainesville",
        "sort-name": "Gainesville",
        "life-span": {
          "ended": null
        }
      },
      "begin-area": {
        "id": "dbe53871-db21-47ea-a0c3-3ab58317a3ab",
        "type": "City",
        "type-id": "6fd8f29a-3d0a-32fc-980d-ea697b69da78",
        "name": "Gainesville",
        "sort-name": "Gainesville",
        "life-span": {
          "ended": null
        }
      },
      "end-area": {
        "id": "dbe53871-db21-47ea-a0c3-3ab58317a3ab",
        "type": "City",
        "type-id": "6fd8f29a-3d0a-32fc-980d-ea697b69da78",
        "name": "Gainesville",
        "sort-name": "Gainesville",
        "life-span": {
          "ended": null
        }
      },
      "disambiguation": "Gainesville, FL Beatles cover band",
      "life-span": {
        "begin": "2005",
        "end": "2015-04-19",
        "ended": true
      }
    },
    {
      "id": "7cac6d47-ef4e-4347-8835-63ed3f2e74a7",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 74,
      "name": "Beatles Back2Back",
      "sort-name": "Beatles Back2Back",
      "country": "AU",
      "area": {
        "id": "106e0bec-b638-3b37-b731-f53d507dc00e",
        "type": "Country",
        "type-id": "06dd0ae4-8c74-30bb-b43d-95dcedf961de",
        "name": "Australia",
        "sort-name": "Australia",
        "life-span": {
          "ended": null
        }
      },
      "disambiguation": "The Beatles tribute band",
      "life-span": {
        "begin": "2011",
        "ended": null
      }
    },
    {
      "id": "dc146c82-7709-41ee-8322-0d4c9fea0378",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 74,
      "name": "Fab Beatles",
      "sort-name": "Fab Beatles",
      "country": "GB",
      "area": {
        "id": "8a754a16-0027-3a29-b6d7-2b40ea0481ed",
        "type": "Country",
        "type-id": "06dd0ae4-8c74-30bb-b43d-95dcedf961de",
        "name": "United Kingdom",
        "sort-name": "United Kingdom",
        "life-span": {
          "ended": null
        }
      },
      "life-span": {
        "ended": null
      }
    },
    {
      "id": "91a7ade0-d51d-4f94-81e8-44b75e67b862",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 74,
      "name": "Capital Beatles",
      "sort-name": "Capital Beatles",
      "begin-area": {
        "id": "bbc88d72-1f32-4936-8dc6-b62b3318e1c4",
        "type": "City",
        "type-id": "6fd8f29a-3d0a-32fc-980d-ea697b69da78",
        "name": "Ottawa",
        "sort-name": "Ottawa",
        "life-span": {
          "ended": null
        }
      },
      "life-span": {
        "begin": "2016",
        "ended": null
      }
    },
    {
      "id": "35574687-3a4d-4b30-a01a-43fea73b3430",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 74,
      "name": "Them Beatles",
      "sort-name": "Them Beatles",
      "country": "GB",
      "area": {
        "id": "8a754a16-0027-3a29-b6d7-2b40ea0481ed",
        "type": "Country",
        "type-id": "06dd0ae4-8c74-30bb-b43d-95dcedf961de",
        "name": "United Kingdom",
        "sort-name": "United Kingdom",
        "life-span": {
          "ended": null
        }
      },
      "disambiguation": "Beatles tribute band",
      "life-span": {
        "ended": null
      },
      "tags": [
        {
          "count": 1,
          "name": "empty"
        }
      ]
    },
    {
      "id": "3badd533-4ce6-4fd6-a5ee-76799406c355",
      "score": 74,
      "name": "Norwegian Beatles",
      "sort-name": "Norwegian Beatles",
      "life-span": {
        "ended": null
      }
    },
    {
      "id": "5d25dbfb-7558-45dc-83dd-6d1176090974",
      "score": 74,
      "name": "Daft Beatles",
      "sort-name": "Daft Beatles",
      "disambiguation": "Mash-up Artist",
      "life-span": {
        "ended": null
      }
    },
    {
      "id": "0f697bc6-6df7-41c9-b550-39981e520d70",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 67,
      "name": "The Beatles Revival Band",
      "sort-name": "Beatles Revival Band, The",
      "country": "DE",
      "area": {
        "id": "85752fda-13c4-31a3-bee5-0e5cb1f51dad",
        "type": "Country",
        "type-id": "06dd0ae4-8c74-30bb-b43d-95dcedf961de",
        "name": "Germany",
        "sort-name": "Germany",
        "life-span": {
          "ended": null
        }
      },
      "begin-area": {
        "id": "16560d3e-358b-4869-8a6d-727db2ee7b69",
        "type": "City",
        "type-id": "6fd8f29a-3d0a-32fc-980d-ea697b69da78",
        "name": "Frankfurt am Main",
        "sort-name": "Frankfurt am Main",
        "life-span": {
          "ended": null
        }
      },
      "disambiguation": "German cover band",
      "life-span": {
        "begin": "1976",
        "ended": null
      },
      "aliases": [
        {
          "sort-name": "Beatles Revival Band",
          "name": "Beatles Revival Band",
          "locale": null,
          "type": null,
          "primary": null,
          "begin-date": null,
          "end-date": null
        }
      ]
    },
    {
      "id": "b06f5860-ce8f-4d8f-92ee-6c6f165ef8ef",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 67,
      "name": "The Silver Beetles",
      "sort-name": "Silver Beetles, The",
      "area": {
        "id": "c249c30e-88ab-4b2f-a745-96a25bd7afee",
        "type": "City",
        "type-id": "6fd8f29a-3d0a-32fc-980d-ea697b69da78",
        "name": "Liverpool",
        "sort-name": "Liverpool",
        "life-span": {
          "ended": null
        }
      },
      "begin-area": {
        "id": "c249c30e-88ab-4b2f-a745-96a25bd7afee",
        "type": "City",
        "type-id": "6fd8f29a-3d0a-32fc-980d-ea697b69da78",
        "name": "Liverpool",
        "sort-name": "Liverpool",
        "life-span": {
          "ended": null
        }
      },
      "end-area": {
        "id": "c249c30e-88ab-4b2f-a745-96a25bd7afee",
        "type": "City",
        "type-id": "6fd8f29a-3d0a-32fc-980d-ea697b69da78",
        "name": "Liverpool",
        "sort-name": "Liverpool",
        "life-span": {
          "ended": null
        }
      },
      "disambiguation": "The name of The Beatles in May-June 1960",
      "life-span": {
        "begin": "1960-05",
        "end": "1960-06",
        "ended": true
      },
      "aliases": [
        {
          "sort-name": "Silver Beatles, The",
          "name": "The Silver Beatles",
          "locale": null,
          "type": null,
          "primary": null,
          "begin-date": null,
          "end-date": null
        }
      ]
    },
    {
      "id": "898e211b-06ad-460b-85ee-cd6ac337ef79",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 66,
      "name": "The Beatles Connection",
      "sort-name": "Beatles Connection, The",
      "country": "DE",
      "area": {
        "id": "85752fda-13c4-31a3-bee5-0e5cb1f51dad",
        "type": "Country",
        "type-id": "06dd0ae4-8c74-30bb-b43d-95dcedf961de",
        "name": "Germany",
        "sort-name": "Germany",
        "life-span": {
          "ended": null
        }
      },
      "begin-area": {
        "id": "5d668739-7f88-4062-9bc5-11a90e838310",
        "type": "City",
        "type-id": "6fd8f29a-3d0a-32fc-980d-ea697b69da78",
        "name": "Braunschweig",
        "sort-name": "Braunschweig",
        "life-span": {
          "ended": null
        }
      },
      "disambiguation": "Beatles Tribute Band",
      "life-span": {
        "begin": "2009",
        "ended": null
      }
    },
    {
      "id": "c271056b-1ca6-49b6-918e-f8f039512f6a",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 66,
      "name": "Henrique Cazes Quarteto",
      "sort-name": "Henrique Cazes Quarteto",
      "country": "BR",
      "area": {
        "id": "f45b47f8-5796-386e-b172-6c31b009a5d8",
        "type": "Country",
        "type-id": "06dd0ae4-8c74-30bb-b43d-95dcedf961de",
        "name": "Brazil",
        "sort-name": "Brazil",
        "life-span": {
          "ended": null
        }
      },
      "begin-area": {
        "id": "f45b47f8-5796-386e-b172-6c31b009a5d8",
        "type": "Country",
        "type-id": "06dd0ae4-8c74-30bb-b43d-95dcedf961de",
        "name": "Brazil",
        "sort-name": "Brazil",
        "life-span": {
          "ended": null
        }
      },
      "life-span": {
        "begin": "1990",
        "ended": null
      },
      "aliases": [
        {
          "sort-name": "Beatles ’n’ Choro",
          "type-id": "1937e404-b981-3cb7-8151-4c86ebfc8d8e",
          "name": "Beatles ’n’ Choro",
          "locale": null,
          "type": "Search hint",
          "primary": null,
          "begin-date": null,
          "end-date": null
        }
      ]
    },
    {
      "id": "d6e9e3d2-b526-4664-8b00-7e0b28b6ed58",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 66,
      "name": "Pete Best Beatles",
      "sort-name": "Pete Best Beatles",
      "country": "AU",
      "area": {
        "id": "106e0bec-b638-3b37-b731-f53d507dc00e",
        "type": "Country",
        "type-id": "06dd0ae4-8c74-30bb-b43d-95dcedf961de",
        "name": "Australia",
        "sort-name": "Australia",
        "life-span": {
          "ended": null
        }
      },
      "disambiguation": "Australia 1980s",
      "life-span": {
        "ended": null
      }
    },
    {
      "id": "7383113a-2b44-4fd1-a2b1-277ca68803b7",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 66,
      "name": "The Better Beatles",
      "sort-name": "Better Beatles, The",
      "begin-area": {
        "id": "7968b302-75a6-4456-a09e-7bf7ac13293a",
        "type": "City",
        "type-id": "6fd8f29a-3d0a-32fc-980d-ea697b69da78",
        "name": "Omaha",
        "sort-name": "Omaha",
        "life-span": {
          "ended": null
        }
      },
      "life-span": {
        "begin": "1981",
        "end": "1981",
        "ended": true
      },
      "tags": [
        {
          "count": 1,
          "name": "new wave"
        }
      ]
    },
    {
      "id": "c658db41-358b-43ba-a06b-8a07ab410407",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 65,
      "name": "The Wife-Beatles",
      "sort-name": "Wife-Beatles, The",
      "area": {
        "id": "05f68b4c-10f3-49b5-b28c-260a1b707043",
        "type": "Subdivision",
        "type-id": "fd3d44c5-80a1-3842-9745-2c4972d35afa",
        "name": "Massachusetts",
        "sort-name": "Massachusetts",
        "life-span": {
          "ended": null
        }
      },
      "life-span": {
        "ended": null
      },
      "tags": [
        {
          "count": 1,
          "name": "rock"
        },
        {
          "count": 1,
          "name": "classic rock"
        }
      ]
    },
    {
      "id": "7d5b2854-ce8f-4d4a-a099-364c1b2488d1",
      "type": "Group",
      "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
      "score": 65,
      "name": "Sgt. Peppercorn's Beatles Marathon",
      "sort-name": "Beatles Marathon",
      "country": "US",
      "area": {
        "id": "489ce91b-6658-3307-9877-795b68554c98",
        "type": "Country",
        "type-id": "06dd0ae4-8c74-30bb-b43d-95dcedf961de",
        "name": "United States",
        "sort-name": "United States",
        "life-span": {
          "ended": null
        }
      },
      "begin-area": {
        "id": "18187bcb-18e6-4075-903e-fb976db17a55",
        "type": "City",
        "type-id": "6fd8f29a-3d0a-32fc-980d-ea697b69da78",
        "name": "Columbus",
        "sort-name": "Columbus",
        "life-span": {
          "ended": null
        }
      },
      "life-span": {
        "begin": "2010-12-10",
        "ended": null
      },
      "aliases": [
        {
          "sort-name": "Sgt. Peppercorn's Marathon",
          "type-id": "894afba6-2816-3c24-8072-eadb66bd04bc",
          "name": "Sgt. Peppercorn's Marathon",
          "locale": null,
          "type": "Artist name",
          "primary": null,
          "begin-date": null,
          "end-date": null
        }
      ]
    },
    {
      "id": "4c3d0136-9235-4c50-b136-be49d17163df",
      "score": 65,
      "name": "The Black Beatles",
      "sort-name": "Black Beatles, The",
      "life-span": {
        "ended": null
      }
    }
  ]
}"""
        private const val DETAIL_RESPONSE = """
{
  "begin_area": {
    "disambiguation": "",
    "iso-3166-2-codes": [
      "GB-LIV"
    ],
    "name": "Liverpool",
    "id": "c249c30e-88ab-4b2f-a745-96a25bd7afee",
    "type": null,
    "sort-name": "Liverpool",
    "type-id": null
  },
  "disambiguation": "",
  "life-span": {
    "ended": true,
    "begin": "1957-03",
    "end": "1970-04-10"
  },
  "id": "b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
  "area": {
    "type": null,
    "type-id": null,
    "sort-name": "United Kingdom",
    "disambiguation": "",
    "iso-3166-1-codes": [
      "GB"
    ],
    "id": "8a754a16-0027-3a29-b6d7-2b40ea0481ed",
    "name": "United Kingdom"
  },
  "name": "The Beatles",
  "ipis": [],
  "release-groups": [
    {
      "id": "6a9ed186-05cd-491c-b71c-4dca5041af29",
      "secondary-type-ids": [
        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
      ],
      "primary-type-id": null,
      "disambiguation": "",
      "secondary-types": [
        "Compilation"
      ],
      "primary-type": null,
      "first-release-date": "1979-05",
      "title": "20 Golden Hits (Original Recordings)"
    },
    {
      "primary-type": "Album",
      "secondary-types": [],
      "first-release-date": "1963-03-22",
      "title": "Please Please Me",
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "id": "de208292-8db5-3aed-a14a-b37a84d8c521",
      "secondary-type-ids": [],
      "disambiguation": ""
    },
    {
      "disambiguation": "",
      "id": "a63dc65f-09f2-359b-a10e-648f00ecd96c",
      "secondary-type-ids": [],
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "title": "With The Beatles",
      "first-release-date": "1963-11-22",
      "secondary-types": [],
      "primary-type": "Album"
    },
    {
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "secondary-type-ids": [],
      "id": "653895d1-b592-3758-8bb1-8b9ba2bd6cb0",
      "disambiguation": "",
      "primary-type": "Album",
      "secondary-types": [],
      "title": "Introducing… The Beatles",
      "first-release-date": "1964-01-10"
    },
    {
      "secondary-types": [],
      "primary-type": "Album",
      "first-release-date": "1964-01-20",
      "title": "Meet The Beatles!",
      "id": "c2c696fc-6beb-3dfb-bb15-7bb021ebeb5d",
      "secondary-type-ids": [],
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "disambiguation": ""
    },
    {
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "id": "31770e0a-e537-376d-a7c0-48e507f3105e",
      "secondary-type-ids": [],
      "disambiguation": "",
      "primary-type": "Album",
      "secondary-types": [],
      "title": "Twist and Shout",
      "first-release-date": "1964-02-03"
    },
    {
      "first-release-date": "1964-04-10",
      "title": "The Beatles’ Second Album",
      "primary-type": "Album",
      "secondary-types": [],
      "disambiguation": "",
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "secondary-type-ids": [],
      "id": "387bc6cc-ac60-365f-819b-fbc78c486065"
    },
    {
      "title": "The Beatles’ Long Tall Sally",
      "first-release-date": "1964-05-11",
      "secondary-types": [],
      "primary-type": "Album",
      "disambiguation": "",
      "id": "d0c93a59-fc4d-3d76-ac38-9c1d11071802",
      "secondary-type-ids": [],
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc"
    },
    {
      "primary-type": "Album",
      "secondary-types": [],
      "title": "Something New",
      "first-release-date": "1964-07-20",
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "secondary-type-ids": [],
      "id": "451e7aa4-a782-3979-9aa0-98c03a246fe9",
      "disambiguation": ""
    },
    {
      "primary-type": "Album",
      "secondary-types": [],
      "first-release-date": "1964-12-04",
      "title": "Beatles for Sale",
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "secondary-type-ids": [],
      "id": "f50a3b6f-27f0-3832-bd3f-3568dc557d95",
      "disambiguation": ""
    },
    {
      "title": "Beatles ’65",
      "first-release-date": "1964-12-15",
      "primary-type": "Album",
      "secondary-types": [],
      "disambiguation": "",
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "secondary-type-ids": [],
      "id": "2d111aea-50f8-33cc-ab63-780bc57068c0"
    },
    {
      "first-release-date": "1965-06-14",
      "title": "Beatles VI",
      "secondary-types": [],
      "primary-type": "Album",
      "disambiguation": "",
      "id": "3d402374-9538-3c09-8566-2850fa3fce3f",
      "secondary-type-ids": [],
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc"
    },
    {
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "id": "dca03435-8adb-30a5-ba82-5a162267ff38",
      "secondary-type-ids": [],
      "disambiguation": "",
      "primary-type": "Album",
      "secondary-types": [],
      "title": "Rubber Soul",
      "first-release-date": "1965-12-03"
    },
    {
      "title": "Revolver",
      "first-release-date": "1966-08-05",
      "secondary-types": [],
      "primary-type": "Album",
      "disambiguation": "",
      "secondary-type-ids": [],
      "id": "72d15666-99a7-321e-b1f3-a3f8c09dff9f",
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc"
    },
    {
      "first-release-date": "1967-06-01",
      "title": "Sgt. Pepper’s Lonely Hearts Club Band",
      "secondary-types": [],
      "primary-type": "Album",
      "disambiguation": "",
      "id": "9f7a4c28-8fa2-3113-929c-c47a9f7982c3",
      "secondary-type-ids": [],
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc"
    },
    {
      "disambiguation": "“The White Album”",
      "id": "055be730-dcad-31bf-b550-45ba9c202aa3",
      "secondary-type-ids": [],
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "first-release-date": "1968-11-22",
      "title": "The Beatles",
      "secondary-types": [],
      "primary-type": "Album"
    },
    {
      "first-release-date": "1969-09-26",
      "title": "Abbey Road",
      "primary-type": "Album",
      "secondary-types": [],
      "disambiguation": "",
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "id": "9162580e-5df4-32de-80cc-f45a8d8a9b1d",
      "secondary-type-ids": []
    },
    {
      "first-release-date": "1970-05-08",
      "title": "Let It Be",
      "secondary-types": [],
      "primary-type": "Album",
      "disambiguation": "",
      "id": "bff544a7-56e0-3ed6-9e0f-3b676cca9111",
      "secondary-type-ids": [],
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc"
    },
    {
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "secondary-type-ids": [],
      "id": "f01b36e3-f01d-4192-bf8d-71fcf08cbee0",
      "disambiguation": "",
      "primary-type": "Album",
      "secondary-types": [],
      "first-release-date": "1976",
      "title": "Rock ’n’ Roll Music"
    },
    {
      "primary-type": "Album",
      "secondary-types": [
        "Compilation"
      ],
      "title": "The Beatles With Tony Sheridan and Guests",
      "first-release-date": "1964-02-03",
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "id": "aa3fd4f5-66a9-43f2-8448-faf402a376c8",
      "secondary-type-ids": [
        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
      ],
      "disambiguation": ""
    },
    {
      "first-release-date": "1964-02-26",
      "title": "Jolly What! England’s Greatest Recording Stars: The Beatles & Frank Ifield on Stage",
      "primary-type": "Album",
      "secondary-types": [
        "Compilation"
      ],
      "disambiguation": "",
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "id": "fd7b1c22-c073-3f06-aa1d-76718bfe578a",
      "secondary-type-ids": [
        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
      ]
    },
    {
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "id": "a4fcc821-2715-3f06-a8eb-a8ee8a7c2772",
      "secondary-type-ids": [
        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
      ],
      "disambiguation": "",
      "primary-type": "Album",
      "secondary-types": [
        "Compilation"
      ],
      "first-release-date": "1964-04",
      "title": "The Beatles Beat"
    },
    {
      "id": "8ee7d50d-3b1d-4d88-a918-1bafb3586191",
      "secondary-type-ids": [
        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
      ],
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "disambiguation": "",
      "secondary-types": [
        "Compilation"
      ],
      "primary-type": "Album",
      "first-release-date": "1964-04",
      "title": "The Beatles’ First!"
    },
    {
      "secondary-types": [
        "Compilation"
      ],
      "primary-type": "Album",
      "first-release-date": "1964-05-22",
      "title": "Les Beatles",
      "id": "6b125ade-4cd3-47b4-ba10-e7959dcdef41",
      "secondary-type-ids": [
        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
      ],
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "disambiguation": ""
    },
    {
      "id": "e992f7ac-5a53-4984-9db9-9bc09979358d",
      "secondary-type-ids": [
        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
      ],
      "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
      "disambiguation": "",
      "secondary-types": [
        "Compilation"
      ],
      "primary-type": "Album",
      "first-release-date": "1964-10-05",
      "title": "Ain’t She Sweet"
    }
  ],
  "begin-area": {
    "disambiguation": "",
    "iso-3166-2-codes": [
      "GB-LIV"
    ],
    "name": "Liverpool",
    "id": "c249c30e-88ab-4b2f-a745-96a25bd7afee",
    "type": null,
    "sort-name": "Liverpool",
    "type-id": null
  },
  "country": "GB",
  "end-area": null,
  "isnis": [
    "0000000121707484"
  ],
  "gender": null,
  "type": "Group",
  "gender-id": null,
  "type-id": "e431f5f6-b5d2-343d-8b36-72607fffb74b",
  "end_area": null,
  "sort-name": "Beatles, The"
}
        """
    }

}