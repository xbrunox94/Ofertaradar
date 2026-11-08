# OfertaRadar — versão inicial Android

Esta é uma versão MVP nativa para Android, com pesquisa de produtos e atalhos para lojas brasileiras.

## O que já funciona
- Interface inicial estilo tecnológico.
- Campo de pesquisa.
- Atalhos para Mercado Livre, Shopee, Amazon Brasil, AliExpress e Magazine Luiza.
- Pesquisa por produto que abre a web.

## Limitações desta versão
Esta versão ainda não coleta preços automaticamente, não roda mineração em segundo plano e não tem integração de IA. Ela abre as pesquisas nas lojas/navegador. Essas funções exigem integrações com APIs/fontes autorizadas e desenvolvimento adicional.

## Como gerar o APK pelo GitHub (sem instalar Android Studio)
1. Crie uma conta no GitHub, se ainda não tiver.
2. Crie um repositório chamado `OfertaRadar`.
3. Envie todos os arquivos desta pasta para o repositório.
4. Abra a aba **Actions**.
5. Se necessário, habilite workflows.
6. Execute **Build OfertaRadar APK** (Run workflow).
7. Quando terminar, abra a execução e baixe o artefato **OfertaRadar-APK**.
8. Dentro do ZIP baixado estará `app-debug.apk`. Baixe no Android e abra para instalar.
   Se o Android bloquear, permita a instalação pelo navegador/gerenciador de arquivos usado.

## Build local
Com Java 17 e Gradle instalado:
`gradle assembleDebug`

APK gerado em:
`app/build/outputs/apk/debug/app-debug.apk`
