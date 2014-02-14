'''
Created on 13.2.2014

@author: Viivi
'''

#TESTAA PURKAMISTA: 5f32271247001a0a470a0a010037022f160a2612090047322f00271e122f00161e3716022f020026125f12572a1e1a02470057020026122f2f0a47004f02122f2f0202002702262f022a16260a47002f2a4f0a1a02024705

import sys

def luo_koodit(aakkoset):
    i = 1 #jarjestysluku
    luku = 0 #koodi
    lista = []
    for c in aakkoset:
        if i % 2 == 1: # A, C, E, G...
            luku = i * 2
        else: # B, D, F, H...
            luku = ((32 - i) * 4) - 1
        tupl = c, luku
        lista.append(tupl)
        i += 1
    return lista

def muunna_heksaluvuksi(luku):
    heksakoodi = hex(luku)
    osat = heksakoodi.split("x")
    heksa = ""
    if len(osat[1]) == 1:
        heksa = "0" + osat[1]
    else:
        heksa = osat[1]
    return heksa

def muunna_desimaaliluvuksi(heksa):
    return int(heksa, 16)

def koodaa(pienet_aakkoset, isot_aakkoset, valimerkit):
    syote = input("Anna koodattava teksti:\n")
    koodi = ""
    koodattu_teksti = ""
    for c in syote:
        for d in pienet_aakkoset:
            if c == d[0]:
                koodi = muunna_heksaluvuksi(d[1])
        for e in isot_aakkoset:
            if c == e[0]:
                koodi = muunna_heksaluvuksi(e[1])
        for f in valimerkit:
            if c == f[0]:
                koodi = f[1]
        koodattu_teksti += koodi
    return koodattu_teksti
    
def pura(pienet_aakkoset, valimerkit):
    syote = input("Anna purettava teksti:\n")
    merkki = ""
    purettu_teksti = ""
    i = 0
    while i+1 < len(syote):
        b = syote[i]
        c = syote[i+1]
        for d in pienet_aakkoset:
            if muunna_desimaaliluvuksi(b+c) == d[1]:
                merkki = d[0]
        for f in valimerkit:
            if (b+c) == f[1]:
                merkki = f[0]
        i += 2
        purettu_teksti += merkki
    return purettu_teksti
    
def main():
    vastaus = input("Mika on vastaus suureen kysymykseen elamasta, maailmankaikkeudesta ja muusta sellaisesta?\n")
    if not vastaus == "42":
        sys.exit()
    else:
        pienet_aakkoset = luo_koodit("abcdefghijklmnopqrstuvwxyz")
        '''
        for c in pienet_aakkoset:
            print(c)
            heksa = muunna_heksaluvuksi(c[1])
            print(heksa)
            luku = muunna_desimaaliluvuksi(heksa)
            print(luku)
        '''
        isot_aakkoset = luo_koodit("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
        valimerkit = [(",", "01"), (".", "05"), ("!", "09"), ("?", "13"), (" ", "00")]
        
        toiminto = 0
        while not toiminto == 3:
            print("Valitse toiminto syottamalla numero:")
            print("1 Koodaa")
            print("2 Pura")
            toiminto = int(input("3 Lopeta\n"))
            if toiminto == 1:
                print(koodaa(pienet_aakkoset, isot_aakkoset, valimerkit))
            elif toiminto == 2:
                print(pura(pienet_aakkoset, valimerkit))
   
main()