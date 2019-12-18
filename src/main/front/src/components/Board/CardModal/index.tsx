import React, {Dispatch, SetStateAction, useState} from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import {CardViewModel} from "../../../models/CardViewModel";
import {addCard} from "../../../httpClient";
import {CardAddOrEditModel} from "../../../models/CardAddOrEditModel";
import report from "../../../utils/report";

interface Props {
    card?: CardViewModel
    isOpen: boolean
    setOpen: Dispatch<SetStateAction<boolean>>
}
const CardModal: React.FC<Props> = ({isOpen, setOpen, card}) => {
    const [title, setTitle] = useState(card ? card.title : "");
    // const [participantsIds, setParticipantsIds] = useState(card ? card.participants.map(p => p.id) : []);
    // const [tagIds, setTagIds] = useState(card ? card.tags.map(t => t.id) : []);
    const onClose = () => setOpen(false);
    const onEdit = async () => {
        onClose()
    };
    const onAdd = async () => {
        try {
            await addCard({
                title,
                participantsIds: [],
                tagIds: [],
            } as CardAddOrEditModel);
            onClose()
        } catch (e) {
            report(e)
        }
    };

    return (
        <Dialog open={isOpen} onClose={onClose} aria-labelledby="form-dialog-title">
            <DialogTitle id="form-dialog-title">
                {card ? "Edit card" : "Add card"}
            </DialogTitle>
            <DialogContent>
                <DialogContentText>
                    To subscribe to this website, please enter your email address here. We will send updates
                    occasionally.
                </DialogContentText>
                <TextField
                    autoFocus
                    margin="dense"
                    id="title"
                    label="Title"
                    type="text"
                    fullWidth
                    value={title}
                    onChange={e => setTitle(e.target.value)}
                />
                <TextField
                    id="startDate"
                    label="Start date"
                    type="date"
                    onChange={e => console.log(e.target.value)}
                    InputLabelProps={{
                        shrink: true,
                    }}
                />
                <TextField
                    id="endDate"
                    label="End date"
                    type="date"
                    InputLabelProps={{
                        shrink: true,
                    }}
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose} color="primary">
                    Cancel
                </Button>
                <Button onClick={card ? onEdit : onAdd} color="primary">
                    {card ? "Edit" : "Add"}
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default CardModal
