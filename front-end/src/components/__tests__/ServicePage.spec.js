import { describe, it, expect } from 'vitest'

import { mount } from '@vue/test-utils'
import ServicePage from '@/components/ServicePage.vue'

describe('ServicePage.vue', () => {
  it('renders a header with instructions', () => {
    const wrapper = mount(ServicePage)
    const topHeader = wrapper.find('#topHeader')
    expect(topHeader.text()).toContain(
      "Let's go! Call a Spring Boot REST backend service, by selecting an input and clicking the button:"
    )
  })
})

describe('ServicePage.vue', () => {
  it('renders a form select with valid language options', () => {
    const wrapper = mount(ServicePage)
    const vSelectLanguage = wrapper.find('#vSelectLanguage')
    expect(vSelectLanguage.attributes().label).toEqual('-- Please select a language --')
    expect(vSelectLanguage.attributes().items).toEqual('C,PHP,Java,Python,JavaScript')
  })
})

describe('ServicePage.vue', () => {
  it('renders a form select with valid sorting options', () => {
    const wrapper = mount(ServicePage)
    const vSelectSorting = wrapper.find('#vSelectSorting')
    expect(vSelectSorting.attributes().label).toEqual('-- Please select a sorting method --')
    expect(vSelectSorting.attributes().items).toEqual('Stars,Interactions,Reactions')
  })
})
