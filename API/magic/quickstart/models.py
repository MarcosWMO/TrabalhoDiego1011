from django.db import models

class Card(models.Model):
    card_nome = models.CharField(max_length = 30)
    card_descricao_poder = models.CharField(max_length = 200)
    card_forca = models.IntegerField(default=1)
    card_agilidade = models.IntegerField(default=1)
    card_resistencia = models.IntegerField(default=1)

    def __str__(self):
        return self.card_nome

