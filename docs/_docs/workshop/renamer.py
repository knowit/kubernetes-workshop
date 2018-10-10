import os

# USAGE: Just run this file. It just echoes commands, it does nothing harmful.
# Config it by entering desired file name order below.

file_order = []
file_order.append("intro.md")
file_order.append("kubectl.md")
file_order.append("node.md")
file_order.append("helm.md")
file_order.append("pod.md")
file_order.append("namespace.md")
file_order.append("label.md")
file_order.append("deploy.md")
file_order.append("probe.md")
file_order.append("service.md")
file_order.append("service-discovery.md")
file_order.append("ingress.md")
file_order.append("configmap.md")
file_order.append("secret.md")
file_order.append("persistentvolumeclaim.md")

print(file_order)

def index_of(file_to_find):
  for i, filename in enumerate(file_order):
    #print(str(i) + " - " + filename + " - " + file_to_find)
    if (filename == file_to_find):
      return i + 1

  return -1

def padded_index(i):
  return i.rjust(2).replace(' ', '0')

for filename in os.listdir("."):
  #print("index: " + str(index_of(filename)))
  file_without_index = filename[3:]

  if file_without_index[-3:] == ".md":
    #print(filename)
    #print(index_of(filename))
    if (index_of(file_without_index) == -1):
      exit('could not find: ' + filename + " - " + file_without_index)

    current_file_index_without_padding = str(index_of(file_without_index))
    current_file_index = padded_index(current_file_index_without_padding)
    new_filename = current_file_index + "-" + file_without_index
    if (filename != new_filename):
      print("mv " + filename + " " + new_filename)
