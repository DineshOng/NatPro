import fileinput
import io

from nltk import pos_tag, word_tokenize
from nltk.stem.wordnet import WordNetLemmatizer
from nltk.tag.mapping import map_tag

__author__ = "David S. Batista"
__email__ = "dsbatista@inesc-id.pt"


class Reverb(object):

    def __init__(self):
        self.lmtzr = WordNetLemmatizer()
        self.aux_verbs = ['be']

    @staticmethod
    def extract_reverb_patterns_tagged_ptb(tagged_text):
        """
        Extract ReVerb relational patterns
        http://homes.cs.washington.edu/~afader/bib_pdf/emnlp11.pdf
        """

        # The pattern limits the relation to be a verb (e.g., invented),
        # a verb followed immediately by a preposition (e.g., located in),
        # or a verb followed by nouns, adjectives, or adverbs ending in a
        # preposition (e.g., has an atomic weight of).

        # V | V P | V W*P
        # V = verb particle? adv?
        # W = (noun | adj | adv | pron | det)
        # P = (prep | particle | inf. marker)

        patterns = []
        patterns_tags = []
        i = 0
        limit = len(tagged_text)-1
        tags = tagged_text

        verb = ['VB', 'VBD', 'VBD|VBN', 'VBG', 'VBG|NN', 'VBN', 'VBP',
                'VBP|TO', 'VBZ', 'VP']
        adverb = ['RB', 'RBR', 'RBS', 'RB|RP', 'RB|VBG', 'WRB']
        particule = ['POS', 'PRT', 'TO', 'RP']
        noun = ['NN', 'NNP', 'NNPS', 'NNS', 'NN|NNS', 'NN|SYM', 'NN|VBG', 'NP']
        adjectiv = ['JJ', 'JJR', 'JJRJR', 'JJS', 'JJ|RB', 'JJ|VBG']
        pronoun = ['WP', 'WP$', 'PRP', 'PRP$', 'PRP|VBP']
        determiner = ['DT', 'EX', 'PDT', 'WDT']
        adp = ['IN', 'IN|RP']

        # TODO: detect negations
        # ('rejected', 'VBD'), ('a', 'DT'), ('takeover', 'NN')

        while i <= limit:
            tmp = io.StringIO()
            tmp_tags = []

            # a ReVerb pattern always starts with a verb
            if tags[i][1] in verb:

                tmp.write(tags[i][0]+' ')
                t = (tags[i][0], tags[i][1])
                tmp_tags.append(t)
                i += 1

                # V = verb particle? adv? (also capture auxiliary verbs)
                while i <= limit and (tags[i][1] in verb or tags[i][1] in adverb or tags[i][1] in particule):
                    tmp.write(tags[i][0]+' ')
                    t = (tags[i][0], tags[i][1])
                    tmp_tags.append(t)
                    i += 1

                # W = (noun | adj | adv | pron | det)
                while i <= limit and (tags[i][1] in noun or tags[i][1] in adjectiv or tags[i][1] in adverb or
                                      tags[i][1] in pronoun or tags[i][1] in determiner):
                    tmp.write(tags[i][0]+' ')
                    t = (tags[i][0], tags[i][1])
                    tmp_tags.append(t)
                    i += 1

                # P = (prep | particle | inf. marker)
                while i <= limit and (tags[i][1] in adp or tags[i][1] in particule):
                    tmp.write(tags[i][0]+' ')
                    t = (tags[i][0], tags[i][1])
                    tmp_tags.append(t)
                    i += 1

                # add the build pattern to the list collected patterns
                patterns.append(tmp.getvalue())
                patterns_tags.append(tmp_tags)
            i += 1

        # Finally, if the pattern matches multiple adjacent sequences, we merge
        # them into a single relation phrase (e.g.,wants to extend).
        #
        # This refinement enables the model to readily handle relation phrases
        # containing multiple verbs.

        merged_patterns_tags = [
            item for sublist in patterns_tags for item in sublist
            ]
        return merged_patterns_tags

    def detect_passive_voice(self, pattern):
        passive_voice = False

        # TODO: there more complex exceptions, adjectives or adverbs in between
        # (to be) + (adj|adv) + past_verb + by
        # to be + past verb + by

        if len(pattern) >= 3:
            if pattern[0][1].startswith('V'):
                verb = self.lmtzr.lemmatize(pattern[0][0], 'v')
                if verb in self.aux_verbs:
                    if (pattern[1][1] == 'VBN' or pattern[1][1] == 'VBD') \
                            and pattern[-1][0] == 'by':
                        passive_voice = True

                    # past verb + by
                    elif (pattern[-2][1] == 'VBN' or pattern[-2][1] == 'VBD') \
                            and pattern[-1][0] == 'by':
                        passive_voice = True

                # past verb + by
                elif (pattern[-2][1] == 'VBN' or pattern[-2][1] == 'VBD') \
                        and pattern[-1][0] == 'by':
                        passive_voice = True

        # past verb + by
        elif len(pattern) >= 2:
            if (pattern[-2][1] == 'VBN' or pattern[-2][1] == 'VBD') \
                    and pattern[-1][0] == 'by':
                passive_voice = True

        return passive_voice

def main():
    reverb = Reverb()
    for line in fileinput.input():
        tokens = word_tokenize(line.strip())
        tokens_tagged = pos_tag(tokens)
        print(tokens_tagged)
        pattern_tags = reverb.extract_reverb_patterns_tagged_ptb(tokens_tagged)
        print(pattern_tags)
        if reverb.detect_passive_voice(pattern_tags):
            print("Passive Voice: True")
        else:
            print("Passive Voice: False")
        print("\n")
    fileinput.close()

if __name__ == "__main__":
    main()
