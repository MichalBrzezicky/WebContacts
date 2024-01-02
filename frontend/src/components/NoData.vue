<template>
  <div class="ma-16">
    <v-alert
      class="mx-auto"
      max-width="700"
      border
      color="primary"
      type="info"
      title="Vítejte XXX!"
      variant="tonal"
    >
      <template v-slot:text>
        Zatím tu není žádná složka. Nejprve si vytvořte první složku pomocí tlačítka '+' v panelu výše.
        Do složky následně přidejte Vaše kontakty.<br><br>
        <span class="font-italic">Složky si pojmenujte dle vlastního uvážení, např: pracovní, rodinné kontakty apod.</span>
      </template>
    </v-alert>
  </div>

</template>

<script>

</script>
