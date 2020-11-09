from django.contrib.auth.models import User, Group
from rest_framework import serializers
from .models import Card

class UserSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = User
        fields = ['url','username','email','groups']

class GroupSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Group
        fields = ['url','name']

class CardSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Card
        fields = ['card_nome','card_descricao_poder','card_forca','card_agilidade','card_resistencia']