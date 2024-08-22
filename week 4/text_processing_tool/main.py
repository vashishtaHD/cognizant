from text_processing_tool import count_words, find_unique_words, convert_to_uppercase

text = input("Enter a text string: ")

print(f"Number of words: {count_words(text)}")
print(f"Unique words: {', '.join(find_unique_words(text))}")
print(f"Text in uppercase: {convert_to_uppercase(text)}")