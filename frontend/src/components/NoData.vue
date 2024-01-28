<template>
  <div :class="phoneNumbers ? 'ma-2' : 'ma-16'">
    <v-alert
      class="mx-auto"
      :max-width="phoneNumbers ? '' : 700"
      border
      color="primary"
      type="info"
      :title="title"
      variant="tonal"
    >
      <template v-slot:text>
        {{text}}<br><br>
        <span class="font-italic">{{info}}</span>
      </template>
    </v-alert>
  </div>

</template>

<script>
  import UserMixin from "@/mixins/UserMixin.js";
 export default {
   name: 'NoData',
   mixins: [UserMixin],
   props: {
     folders: {
       type: Boolean,
       default: false,
     },
     contacts: {
       type: Boolean,
       default: false,
     },
     phoneNumbers: {
       type: Boolean,
       default: false,
     }
   },

   computed: {
     title() {
       if (this.folders) return 'Vítejte ' + this.getLoggedUser.name + ' ' + this.getLoggedUser.surname + '!'
       if (this.contacts || this.phoneNumbers) return 'Nic se tu nenašlo!'
     },

     text() {
       if (this.folders) return "Zatím tu není žádná složka. Vytvořte si první složku pomocí tlačítka '+' v panelu výše. Do složky následně přidejte Vaše kontakty."
       if (this.contacts) return "Tato složka neobsahuje žádný kontakt. Vytvořte si první kontakt pomocí tlačítka '+' výše."
       if (this.phoneNumbers) return "Tento kontakt neobsahuje žádné telefonní číslo."
     },

     info() {
       if (this.folders) return "Složky si pojmenujte dle vlastního uvážení, např: pracovní, rodinné kontakty apod."
     },
   }
 }
</script>
