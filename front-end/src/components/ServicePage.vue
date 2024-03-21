<template>
  <div>
    <p></p>
    <h6 id="topHeader">
      <v-chip class="rounded-sm" variant="elevated" color="primary"> Let's go!</v-chip>
      Call a Spring Boot REST backend service, by selecting an input and clicking the button:
    </h6>
    <p></p>
    <div class="mx-auto w-50">
      <v-select
        class="mx-auto w-50"
        id="vSelectLanguage"
        v-model="selectedLanguage"
        label="-- Please select a language --"
        :items="['C', 'PHP', 'Java', 'Python', 'JavaScript']"
      ></v-select>
      <v-select
        class="mx-auto w-50"
        id="vSelectSorting"
        v-model="selectedSorting"
        label="-- Please select a sorting method --"
        :items="['Stars', 'Interactions', 'Reactions']"
      ></v-select>
    </div>
    <p></p>
    <v-btn
      color="green"
      ref="bFetchButton"
      v-if="selectedLanguage != null && selectedSorting != null"
      variant="flat"
      @click="callListRestService()"
      >Click to retrieve top 10 GitHub repositories
    </v-btn>
    <p></p>
    <div id="div-loading" v-if="fetching" class="mx-auto">
      <v-progress-circular indeterminate class="mr-2" :size="128" :width="12"></v-progress-circular>
      <h3>Loading...</h3>
    </div>
    <p></p>
    <div>
      <v-container>
        <v-row class="cards-row justify-center" no-gutters>
          <v-col v-for="(item, i) in restResponse" v-bind:key="item.id">
            <v-card
              class="d-flex flex-column mx-1 my-1 h-100"
              :title="'#' + (i + 1) + ' ' + item.name"
            >
              <v-img contain :src="item.owner.avatarUrl" max-height="30%"></v-img>
              <v-card-text class="text-left" height="100%">
                {{ item.description }}
              </v-card-text>
              <v-btn class="mx-1 my-1" variant="flat" color="blue" :href="item.htmlUrl"
                >Read more</v-btn
              >
              <v-divider></v-divider>
              <v-card-actions>
                <v-card-text class="text-disabled bg-grey-lighten-3"
                  >Followers {{ item.watchersCount }}</v-card-text
                >
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'ServicePage',

  data() {
    return {
      selectedLanguage: null,
      selectedSorting: null,
      fetching: false,
      postBody: '',
      restResponse: '',
      errors: []
    }
  },

  methods: {
    callListRestService() {
      // before a request is made, start the loading spinner
      axios.interceptors.request.use((config) => {
        this.fetching = true
        return config
      })

      // before a response is returned, stop the loading spinner
      axios.interceptors.response.use((response) => {
        this.fetching = false
        return response
      })

      axios
        .post(
          `/api/repositories`,
          JSON.parse(
            '{"language":"' +
              this.selectedLanguage +
              '", "sortingBy":"' +
              this.selectedSorting +
              '"}'
          )
        )
        .then((response) => {
          // JSON responses are automatically parsed.
          this.restResponse = response.data.items
        })
        .catch((e) => {
          this.errors.push(e)
        })
    }
  }
}
</script>
